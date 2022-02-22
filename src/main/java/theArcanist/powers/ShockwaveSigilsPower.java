package theArcanist.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class ShockwaveSigilsPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("ShockwaveSigils");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ShockwaveSigilsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onDiscardSigil() {
        forAllMonstersLiving(m -> applyToEnemy(m, new CrushedPower(m, amount)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}