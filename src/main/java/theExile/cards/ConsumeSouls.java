package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.powers.SoulFeedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ConsumeSouls extends AbstractExileCard {
    public final static String ID = makeID(ConsumeSouls.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;

    public ConsumeSouls() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SoulFeedPower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}