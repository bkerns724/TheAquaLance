package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getDebuffCount;
import static theExile.util.Wiz.getSlashEffect;

public class StoneDaggers extends AbstractExileCard {
    public final static String ID = makeID(StoneDaggers.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public StoneDaggers() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
    }

    public void singleTargetUse(AbstractMonster m) {
        int n = getDebuffCount(m);
        for (int i = 0; i<n; i++)
            dmg(m, getSlashEffect(damage));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (mo == null)
            magicNumber = 0;
        else if (getDebuffCount(mo) == 1)
            magicNumber = getDebuffCount(mo);
    }

    @Override
    public void initializeDescription() {
        if (cardStrings == null) {
            rawDescription = "SHOULD NOT BE SEEN";
            return;
        }
        if (magicNumber <= 0)
            rawDescription = cardStrings.DESCRIPTION;
        else if (magicNumber == 1)
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        else
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
        if (sigil)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[1] + rawDescription;

        overrideRawDesc = true;
        super.initializeDescription();
    }
}