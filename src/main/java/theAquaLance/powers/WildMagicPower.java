package theAquaLance.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theAquaLance.AquaLanceMod;

import static theAquaLance.util.Wiz.*;

public class WildMagicPower extends AbstractEasyPower {
    public static String POWER_ID = AquaLanceMod.makeID("WildMagic");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WildMagicPower(AbstractCreature owner, int amount) {
        super("WildMagic", PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving(m -> att(new DamageAction(m,
                new DamageInfo(adp(), amount, DamageInfo.DamageType.HP_LOSS), AquaLanceMod.Enums.WATER)));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}