package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.BanePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Bane extends AbstractExileCard {
    public final static String ID = makeID(Bane.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 2;
    private final static int UPGRADED_COST = 1;

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
        applyToEnemy(m, new BanePower(m, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}