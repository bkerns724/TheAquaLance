package theArcanist.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class IronSigil extends AbstractSigilCard {
    public final static String ID = makeID("IronSigil");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public IronSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        applyToSelf(new MetallicizePower(AbstractDungeon.player, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}