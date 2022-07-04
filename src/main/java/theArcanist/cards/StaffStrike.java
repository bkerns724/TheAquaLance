package theArcanist.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;

public class StaffStrike extends AbstractArcanistCard {
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

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, getBluntEffect());
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new StaffStrike();
    }
}