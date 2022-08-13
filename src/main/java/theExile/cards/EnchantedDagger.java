package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class EnchantedDagger extends AbstractExileCard {
    public final static String ID = makeID(EnchantedDagger.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;

    public EnchantedDagger() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void applyPowers() {
        damageModList.clear();
        DamageModifierManager.clearModifiers(this);
        for (AbstractCard card : adp().hand.group) {
            if (card instanceof AbstractExileCard && card != this)
                addModifier(((AbstractExileCard) card).damageModList, true);
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}