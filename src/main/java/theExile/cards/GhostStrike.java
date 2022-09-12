package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.EnergizedExilePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class GhostStrike extends AbstractExileCard {
    public final static String ID = makeID(GhostStrike.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public GhostStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        tags.add(CardTags.STRIKE);
        addModifier(elenum.PHANTASMAL);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new EnergizedExilePower(magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}