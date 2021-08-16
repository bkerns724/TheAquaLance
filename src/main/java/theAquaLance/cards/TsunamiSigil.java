package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.TsunamiAction;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;
import theAquaLance.patches.DiscardHookPatch.GameActionManagerField;

import static theAquaLance.util.Wiz.*;
import static theAquaLance.AquaLanceMod.makeID;

public class TsunamiSigil extends AbstractSigilCard {
    public final static String ID = makeID("TsunamiSigil");
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_DAMAGE = 4;
    private final static int UPGRADE_SECOND = 1;

    public TsunamiSigil() {
        super(ID, CardRarity.RARE);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondDamage = SECOND_DAMAGE;
        AbstractCardField.sigil.set(this, true);
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        calculateCardDamage(null);
        att(new TsunamiAction(this));
    }

    public void applyPowers() {
        int sigilCount = GameActionManagerField.sigilsThisCombat.get(AbstractDungeon.actionManager);
        baseSecondDamage = magicNumber*sigilCount;
        super.applyPowers();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondDamage(UPGRADE_SECOND);
    }
}