package theExile.cards;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class Quill extends AbstractExileCard {
    public final static String ID = makeID(Quill.class.getSimpleName());
    private final static int DAMAGE = 2;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 0;

    public Quill() {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.size() == 0)
            dmg(m, Wiz.getSlashEffect(damage));
        else
            dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}