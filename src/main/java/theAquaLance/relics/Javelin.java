package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theAquaLance.TheAquaLance;
import theAquaLance.powers.StreamlinedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Javelin extends AbstractEasyRelic {
    public static final String ID = makeID("Javelin");
    private static final int STREAM_AMT = 1;
    private static final int NUM_CARDS = 3;

    public Javelin() {
        super(ID, RelicTier.UNCOMMON, LandingSound.HEAVY, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + NUM_CARDS + DESCRIPTIONS[1] + STREAM_AMT + DESCRIPTIONS[2];
    }

    public void atTurnStart() {
        counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            ++counter;
            if (counter % NUM_CARDS == 0) {
                counter = 0;
                flash();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new ApplyPowerAction(adp(), adp(), new StreamlinedPower(adp(), STREAM_AMT), STREAM_AMT));
            }
        }

    }

    public void onVictory() {
        counter = -1;
    }
}