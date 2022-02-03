package theArcanist.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcanist.powers.TempHPOnHitPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class BlackSigil extends AbstractSigilCard {
    public final static String ID = makeID("BlackSigil");
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;

    public BlackSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        applyToSelf(new TempHPOnHitPower(AbstractDungeon.player, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}