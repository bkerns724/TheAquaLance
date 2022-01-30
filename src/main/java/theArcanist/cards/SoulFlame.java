package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod.Enums;

import static theArcanist.util.Wiz.*;
import static theArcanist.ArcanistMod.makeID;

public class SoulFlame extends AbstractArcanistCard {
    public final static String ID = makeID("SoulFlame");
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public SoulFlame() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, Enums.SOUL_FIRE);
        atb(new DrawCardAction(magicNumber));
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}