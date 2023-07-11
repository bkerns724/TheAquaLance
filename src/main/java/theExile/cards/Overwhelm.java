package theExile.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Overwhelm extends AbstractExileCard {
    public final static String ID = makeID(Overwhelm.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 3;
    private final static int UGPRADE_MAGIC = 1;
    private final static int COST = 1;

    public Overwhelm() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    public void singleTargetUse(AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int count = adp().hand.size();
                int temp = baseDamage;
                baseDamage += magicNumber * count;
                calculateCardDamage(m);
                dmgTop(m);
                baseDamage = temp;
                att(new DiscardAction(adp(), adp(), count, true));
            }
        });
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UGPRADE_MAGIC);
    }
}