package theExile.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.ICE;
import static theExile.util.Wiz.atb;

public class FrozenLance extends AbstractExileCard {
    public final static String ID = makeID(FrozenLance.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 0;
    private final static int MAGIC = 1;

    public FrozenLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        addModifier(ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        atb(new DiscardAction(p, p, magicNumber, false));
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}