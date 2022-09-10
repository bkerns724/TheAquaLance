package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class HeavySigil extends AbstractExileCard {
    public final static String ID = makeID(HeavySigil.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

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
        applyToSelf(new DexterityPower(adp(), magicNumber));
        applyToSelf(new LoseDexterityPower(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}