package theArcanist.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcanist.powers.ShadowcloakPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChameleonSigil extends AbstractSigilCard {
    public final static String ID = makeID("ChameleonSigil");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public ChameleonSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        applyToSelf(new ShadowcloakPower(AbstractDungeon.player, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}