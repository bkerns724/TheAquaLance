package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Voices extends AbstractExileCard {
    public final static String ID = makeID(Voices.class.getSimpleName());
    private final static int COST = 1;

    public Voices() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int effect = getJinxAmountCard(m);
        if (upgraded)
            effect++;
        if (effect > 0)
            applyToEnemy(m, new VulnerablePower(m, effect, false));
    }

    public void upp() {
    }
}