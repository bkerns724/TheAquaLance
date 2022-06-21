package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.discard;
import static theArcanist.util.Wiz.cardDraw;

public class FrostBolt extends AbstractArcanistCard {
    public final static String ID = makeID(FrostBolt.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public FrostBolt() { super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY); }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        cardDraw(magicNumber);
        discard(magicNumber);

    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}