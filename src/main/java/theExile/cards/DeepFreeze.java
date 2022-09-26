package theExile.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ICE;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class DeepFreeze extends AbstractExileCard {
    public final static String ID = makeID(DeepFreeze.class.getSimpleName());
    private final static int DAMAGE = 20;
    private final static int UPGRADE_DAMAGE = 7;
    private final static int MAGIC = 2;
    private final static int COST = 2;

    public DeepFreeze() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(ICE);
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void nonTargetUse() {
        DrawReductionPower power = new DrawReductionPower(adp(), magicNumber);
        ReflectionHacks.setPrivate(power, DrawReductionPower.class, "justApplied", false);
        applyToSelf(power);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}