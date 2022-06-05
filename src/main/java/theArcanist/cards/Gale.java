package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.getRandomSlash;

public class Gale extends AbstractArcanistCard {
    public final static String ID = makeID(Gale.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 2;

    public Gale() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    protected AbstractGameAction.AttackEffect getDefaultAttackEffect() {
        if (damage > 15)
            return AbstractGameAction.AttackEffect.SLASH_HEAVY;
        return getRandomSlash();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}