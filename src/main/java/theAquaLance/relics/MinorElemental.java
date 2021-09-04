package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    @Override
    public void atTurnStart() {
        flash();
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        forAllMonstersLiving(m -> {
            helper.calculateCardDamage(m);
            atb(new DamageAction(m, new DamageInfo(adp(), helper.secondDamage, AquaLanceMod.Enums.MAGIC),
                    AquaLanceMod.Enums.WATER));
        });
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DAM_AMT + DESCRIPTIONS[1];
    }
}