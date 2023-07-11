package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class StaticShocks extends AbstractExileCard {
    public final static String ID = makeID(StaticShocks.class.getSimpleName());
    private final static int DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public StaticShocks() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmg(m);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}