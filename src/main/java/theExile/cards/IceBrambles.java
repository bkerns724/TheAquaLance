package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class IceBrambles extends AbstractExileCard {
    public final static String ID = makeID(IceBrambles.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public IceBrambles() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(elenum.ICE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new ThornsPower(adp(), magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}