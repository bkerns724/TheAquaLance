package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod.Enums;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;

public class SoulFlame extends AbstractArcanistCard {
    public final static String ID = makeID(SoulFlame.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public SoulFlame() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FIRE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, Enums.SOUL_FIRE);
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}