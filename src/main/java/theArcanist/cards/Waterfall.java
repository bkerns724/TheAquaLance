package theArcanist.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.WaterfallAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Waterfall extends AbstractArcanistCard {
    public final static String ID = makeID("Waterfall");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public Waterfall() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new WaterfallAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}