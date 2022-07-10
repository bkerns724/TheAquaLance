package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.actions.MyPlayTopCardAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ChaoticFlurry extends AbstractExileCard {
    public final static String ID = makeID(ChaoticFlurry.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public ChaoticFlurry() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new MyPlayTopCardAction(false));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}