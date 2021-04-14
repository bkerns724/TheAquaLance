package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;
import theAquaLance.patches.GameActionManagerPatch.GameActionManagerField;

import static theAquaLance.util.Wiz.*;
import static theAquaLance.AquaLanceMod.makeID;

public class TsunamiSigil extends AbstractEasyCard {
    public final static String ID = makeID("TsunamiSigil");
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public TsunamiSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        int sigilCount = GameActionManagerField.sigilsThisCombat.get(AbstractDungeon.actionManager);
        allDmg(AbstractGameAction.AttackEffect.POISON);
    }

    public void applyPowers() {
        int realBaseDamage = baseDamage;
        int sigilCount = GameActionManagerField.sigilsThisCombat.get(AbstractDungeon.actionManager);
        baseDamage = baseDamage*sigilCount;
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}