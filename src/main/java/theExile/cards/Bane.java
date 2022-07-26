package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.getDebuffCount;

public class Bane extends AbstractExileCard {
    public final static String ID = makeID(Bane.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Bane() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new JinxPower(m, magicNumber*getDebuffCount(m)));
    }

    public void upp() {
        exhaust = false;
    }
}