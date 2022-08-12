package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Drain extends AbstractExileCard {
    public final static String ID = makeID(Drain.class.getSimpleName());
    private final static int COST = 1;

    public Drain() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int effect = getJinxAmountCard(m);
        if (upgraded)
            effect++;
        if (effect > 0)
            applyToEnemy(m, new WeakPower(m, effect, false));
    }

    public void upp() {
    }
}