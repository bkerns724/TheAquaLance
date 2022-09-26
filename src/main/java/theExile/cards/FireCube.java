package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theExile.powers.CorrodedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.getFireEffect;

public class FireCube extends AbstractExileCard {
    public final static String ID = makeID(FireCube.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public FireCube() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m, getFireEffect(damage));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower powVigor = adp().getPower(VigorPower.POWER_ID);
        AbstractPower powStrength = adp().getPower(StrengthPower.POWER_ID);
        AbstractPower powCorroded = mo.getPower(CorrodedPower.POWER_ID);
        if (powVigor != null)
            powVigor.amount *= magicNumber;
        if (powStrength != null)
            powStrength.amount *= magicNumber;
        if (powCorroded != null)
            powCorroded.amount *= magicNumber;
        super.calculateCardDamage(mo);
        if (powVigor != null)
            powVigor.amount /= magicNumber;
        if (powStrength != null)
            powStrength.amount /= magicNumber;
        if (powCorroded != null)
            powCorroded.amount /= magicNumber;
    }

    @Override
    public void applyPowers() {
        AbstractPower powVigor = adp().getPower(VigorPower.POWER_ID);
        AbstractPower powStrength = adp().getPower(StrengthPower.POWER_ID);
        if (powVigor != null)
            powVigor.amount *= magicNumber;
        if (powStrength != null)
            powStrength.amount *= magicNumber;
        super.applyPowers();
        if (powVigor != null)
            powVigor.amount /= magicNumber;
        if (powStrength != null)
            powStrength.amount /= magicNumber;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}