package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY;
import static theExile.ExileMod.makeID;

public class LightningBolt extends AbstractExileCard {
    public final static String ID = makeID(LightningBolt.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;

    public LightningBolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.LIGHTNING);
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}