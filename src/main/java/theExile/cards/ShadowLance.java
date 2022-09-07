package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ShadowLance extends AbstractExileCard {
    public final static String ID = makeID(ShadowLance.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 3;

    public ShadowLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ELDRITCH);
        exhaust = true;
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
        applyToEnemy(m, new StrengthPower(m, -magicNumber));
        if (!m.hasPower(ArtifactPower.POWER_ID))
            applyToSelf(new StrengthPower(adp(), magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}