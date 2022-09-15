package theExile.cards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class ElementalProwess extends AbstractExileCard {
    public final static String ID = makeID(ElementalProwess.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public ElementalProwess() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    @Override
    public void nonTargetUse() {
        ArrayList<AbstractCard> elementChoices = new ArrayList<>();
        elementChoices.add(new ForceOption());
        elementChoices.add(new EldritchOption());
        elementChoices.add(new IceOption());
        elementChoices.add(new LightningOption());

        atb(new ChooseOneAction(elementChoices));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}