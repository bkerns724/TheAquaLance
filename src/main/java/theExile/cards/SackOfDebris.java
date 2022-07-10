package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.SackOfDebrisAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SackOfDebris extends AbstractExileCard {
    public final static String ID = makeID(SackOfDebris.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public SackOfDebris() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        selfRetain = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new SackOfDebrisAction(this));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}