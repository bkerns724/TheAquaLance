package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class FrostDaggers extends AbstractExileCard {
    public final static String ID = makeID(FrostDaggers.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 2;
    private final static int COST = 1;

    public FrostDaggers() { super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY); }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}