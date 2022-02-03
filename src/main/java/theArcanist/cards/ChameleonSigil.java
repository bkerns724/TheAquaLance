package theArcanist.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import theArcanist.powers.ShadowCloakPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChameleonSigil extends AbstractSigilCard {
    public final static String ID = makeID("ChameleonSigil");
    private final static int MAGIC = 2;
    private final static int DRAW_REDUCTION = 1;

    public ChameleonSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        applyToSelf(new ShadowCloakPower(AbstractDungeon.player, magicNumber));
        if (!upgraded)
            applyToSelf(new DrawReductionPower(AbstractDungeon.player, DRAW_REDUCTION));
    }

    public void upp() {
    }
}