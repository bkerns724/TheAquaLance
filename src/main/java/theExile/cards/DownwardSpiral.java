package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class DownwardSpiral extends AbstractExileCard {
    public final static String ID = makeID(DownwardSpiral.class.getSimpleName());
    private final static int COST = 1;

    public DownwardSpiral() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int count = getJinxAmountCard(m);
        if (count > 0 ) {
            if (upgraded)
                applyToEnemy(m, new JinxPower(m, 2*count));
            else
                applyToEnemy(m, new JinxPower(m, count));
        }
    }

    public void upp() {
    }
}