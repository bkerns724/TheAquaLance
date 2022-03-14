package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class BoulderShot extends AbstractArcanistCard {
    public final static String ID = makeID("BoulderShot");
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public BoulderShot() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int n = getDebuffCount(m);
        for (int i = 0; i<n; i++)
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}