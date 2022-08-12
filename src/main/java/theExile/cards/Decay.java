package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.DecayPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Decay extends AbstractExileCard {
    public final static String ID = makeID(Decay.class.getSimpleName());
    private final static int COST = 1;

    public Decay() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int count = getJinxAmountCard(m);
        if (count > 0)
            applyToEnemy(m, new DecayPower(m, count));
    }

    public void upp() {
        exhaust = false;
    }
}