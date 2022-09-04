package theExile.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class BlizzardSigil extends AbstractExileCard {
    public final static String ID = makeID(BlizzardSigil.class.getSimpleName());
    private final static int DAMAGE = 16;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 3;
    private final static int COST = -2;

    public BlizzardSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        addModifier(elenum.ICE);
    }

    @Override
    public void nonTargetUse() {
        DamageInfo info = new DamageInfo(adp(), magicNumber, DamageInfo.DamageType.THORNS);
        atb(new DamageAction(adp(), info, ExileMod.Enums.ICE));
    }

    @Override
    public AbstractMonster getTarget() {
        return getLowestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}