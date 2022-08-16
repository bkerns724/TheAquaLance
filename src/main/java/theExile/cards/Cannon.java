package theExile.cards;

import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.shuffleIn;

public class Cannon extends AbstractExileCard {
    public final static String ID = makeID(Cannon.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int MAGIC = 2;
    private final static int SECOND_MAGIC = 2;
    private final static int UPGRADE_SECOND = 1;
    private final static int COST = 0;

    public Cannon() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        addModifier(elenum.LIGHTNING);
        cardsToPreview = new Dazed();
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < secondMagic; i++)
            dmg(m);
        shuffleIn(new Dazed(), magicNumber);
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}