package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theAquaLance.TheAquaLance;
import theAquaLance.cards.AquaSigil;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class RuneOfLivingWater extends AbstractEasyRelic {
    public static final String ID = makeID("RuneOfLivingWater");
    private final static int SIGIL_COUNT = 3;

    public RuneOfLivingWater() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public void obtain() {
        // Below statement should always be true if this relic is being obtained.
        if (AbstractDungeon.player.hasRelic(RuneOfIce.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if ((AbstractDungeon.player.relics.get(i)).relicId.equals(RuneOfIce.ID)) {
                    instantObtain(AbstractDungeon.player, i,true);
                    break;
                }
            }
        }
    }

    @Override
    public void atBattleStart() {
        flash();
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        atb(new MakeTempCardInDrawPileAction(new AquaSigil(), SIGIL_COUNT, true, true));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + SIGIL_COUNT + DESCRIPTIONS[1];
    }

    public boolean CanSpawn() {return adp().hasRelic(RuneOfIce.ID);}
}