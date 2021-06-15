package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theAquaLance.AquaLanceMod;
import theAquaLance.TheAquaLance;
import theAquaLance.cards.EleHelper;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class MinorElemental extends AbstractEasyRelic {
    public static final String ID = makeID("MinorElemental");
    private static final int DAM_AMT = 2;
    private EleHelper helper = new EleHelper();

    public MinorElemental() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public void atTurnStart() {
        helper.applyPowers();
        atb(new DamageAllEnemiesAction(adp(), helper.multiDamageTwo, DamageInfo.DamageType.HP_LOSS,
                AquaLanceMod.Enums.WATER));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DAM_AMT + DESCRIPTIONS[1];
    }
}