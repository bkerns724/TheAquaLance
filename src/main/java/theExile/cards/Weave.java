package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.WeaveAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Weave extends AbstractExileCard {
    public final static String ID = makeID(Weave.class.getSimpleName());
    private final static int COST = 0;

    public Weave() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new WeaveAction(upgraded));
    }

    public void upp() {
    }
}