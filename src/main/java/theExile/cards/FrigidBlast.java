package theExile.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class FrigidBlast extends AbstractExileCard {
    public final static String ID = makeID(FrigidBlast.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 2;
    private final static int COST = 0;

    public FrigidBlast() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ICE);
        cardsToPreview = new Dazed();
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        atb(new MakeTempCardInDrawPileAction(new Dazed(), magicNumber, true, true));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}