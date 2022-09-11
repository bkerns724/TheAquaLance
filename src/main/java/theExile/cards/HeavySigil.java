package theExile.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class HeavySigil extends AbstractExileCard {
    public final static String ID = makeID(HeavySigil.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;

    public HeavySigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        addModifier(elenum.FORCE);
    }

    @Override
    public AbstractMonster getTarget() {
        return getHighestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
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