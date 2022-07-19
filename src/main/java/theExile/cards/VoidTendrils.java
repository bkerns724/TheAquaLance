package theExile.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class VoidTendrils extends AbstractExileCard {
    public final static String ID = makeID(VoidTendrils.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;

    public VoidTendrils() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.DARK);
        cardsToPreview = new VoidCard();
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        atb(new MakeTempCardInDiscardAction(new VoidCard(), 1));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}