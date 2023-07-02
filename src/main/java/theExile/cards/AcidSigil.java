package theExile.cards;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.powers.CorrodedPower;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class AcidSigil extends AbstractExileCard {
    public final static String ID = makeID(AcidSigil.class.getSimpleName());
    private final static int COST = -2;
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;

    public AcidSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public AbstractMonster getTarget() {
        return Wiz.getLowestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m, Wiz.getAcidEffect(damage));
        applyToEnemy(m, new CorrodedPower(m, magicNumber));
    }

    @Override
    public void nonTargetUse() {
        atb(new LoseHPAction(adp(), adp(), magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}