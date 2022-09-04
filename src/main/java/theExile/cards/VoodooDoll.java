package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.VoodooDollAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class VoodooDoll extends AbstractExileCard {
    public final static String ID = makeID(VoodooDoll.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 3;
    private final static int COST = 1;

    public VoodooDoll() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        atb(new VoodooDollAction(magicNumber, this, m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}