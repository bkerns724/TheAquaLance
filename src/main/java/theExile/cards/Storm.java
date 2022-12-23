package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class Storm extends AbstractExileCard {
    public final static String ID = makeID(Storm.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public Storm() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
    }

    public void singleTargetUse(AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
        dmg(m);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}