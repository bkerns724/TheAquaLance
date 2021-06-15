package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;
import theAquaLance.patches.GameActionManagerPatch.GameActionManagerField;

import static theAquaLance.util.Wiz.*;
import static theAquaLance.AquaLanceMod.makeID;

public class TsunamiSigil extends AbstractEasyCard {
    public final static String ID = makeID("TsunamiSigil");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_DAMAGE = 3;
    private final static int UPGRADE_SECOND = 1;

    public TsunamiSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondDamage = SECOND_DAMAGE;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        int sigilCount = GameActionManagerField.sigilsThisCombat.get(AbstractDungeon.actionManager);
        forAllMonstersLiving(m ->
            atb(new DamageAction(m, new DamageInfo(adp(), magicNumber * sigilCount, DamageInfo.DamageType.THORNS),
                    AquaLanceMod.Enums.WATER))
        );
    }

    public void applyPowers() {
        int sigilCount = GameActionManagerField.sigilsThisCombat.get(AbstractDungeon.actionManager) + 1;
        int temp = baseSecondDamage;
        baseSecondDamage = temp*sigilCount;
        super.applyPowers();
        baseSecondDamage = temp;
        isSecondDamageModified = secondDamage != baseSecondDamage;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_SECOND);
    }
}