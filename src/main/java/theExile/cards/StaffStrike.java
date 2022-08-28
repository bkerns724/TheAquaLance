package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getBluntEffect;

public class StaffStrike extends AbstractExileCard {
    public final static String ID = makeID(StaffStrike.class.getSimpleName());
    public final static int DAMAGE = 6;
    public final static int UPGRADE_DAMAGE = 3;

    public StaffStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.isEmpty())
            dmg(m, getBluntEffect(damage));
        else
            dmg(m);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new StaffStrike();
    }
}