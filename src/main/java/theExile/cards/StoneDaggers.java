package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getDebuffCount;

public class StoneDaggers extends AbstractExileCard {
    public final static String ID = makeID(StoneDaggers.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public StoneDaggers() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int n = getDebuffCount(m);
        for (int i = 0; i<n; i++)
            dmg(m, getSlashEffect());
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}