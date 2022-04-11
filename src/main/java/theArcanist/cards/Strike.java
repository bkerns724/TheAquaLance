package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.cards.AbstractArcanistCard.elenum.*;

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
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, getAttackEffect());
    }

    private AbstractGameAction.AttackEffect getAttackEffect() {
        if (damageModList.isEmpty())
            return AbstractGameAction.AttackEffect.BLUNT_LIGHT;
        else if (damageModList.contains(ICE))
            return ArcanistMod.Enums.ICE;
        else if (damageModList.contains(FORCE))
            return ArcanistMod.Enums.FIST;
        else if (damageModList.contains(FIRE))
            return ArcanistMod.Enums.SOUL_FIRE;
        else if (damageModList.contains(DARK))
            return ArcanistMod.Enums.DARK_COIL;
        else
            return AbstractGameAction.AttackEffect.BLUNT_LIGHT;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike();
    }
}