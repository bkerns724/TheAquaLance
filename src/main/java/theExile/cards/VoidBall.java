package theExile.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.AttackAction;
import theExile.actions.DiscardToDoAction;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.DARK;
import static theExile.util.Wiz.atb;

public class VoidBall extends AbstractExileCard {
    public final static String ID = makeID(VoidBall.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public VoidBall() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(DARK);
        magicNumber = baseMagicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractGameAction action = new AttackAction(multiDamage, getAttackEffect());
        atb(new DiscardToDoAction(magicNumber, action, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}