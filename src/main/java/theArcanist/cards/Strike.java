package theArcanist.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.patches.AbstractCardPatch;

import static theArcanist.ArcanistMod.makeID;

public class Strike extends AbstractArcanistCard {
    public final static String ID = makeID(Strike.class.getSimpleName());
    public final static int DAMAGE = 6;
    public final static int UPGRADE_DAMAGE = 3;

    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
        AbstractCardPatch.AbstractCardFields.retreat.set(this, true);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, getBluntEffect());
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike();
    }
}