package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;

public class Strike extends AbstractArcanistCard {
    public final static String ID = makeID("Strike");
    public final static int DAMAGE = 6;
    public final static int UPGRADE_DAMAGE = 3;

    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (damage < 15)
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        else
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}