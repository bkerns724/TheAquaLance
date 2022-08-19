package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class BrrZerk extends AbstractExileCard {
    public final static String ID = makeID(BrrZerk.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 3;

    public BrrZerk() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmg(m);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}