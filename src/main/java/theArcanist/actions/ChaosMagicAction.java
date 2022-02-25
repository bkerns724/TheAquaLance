package theArcanist.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.ForesightPower;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import theArcanist.powers.HundredCutsPower;
import theArcanist.powers.StoneskinPower;

import static theArcanist.util.Wiz.*;

public class ChaosMagicAction extends AbstractGameAction {

    public ChaosMagicAction() {
        duration = DEFAULT_DURATION;
    }

    @Override
    public void update() {
        if (duration != DEFAULT_DURATION) {
            isDone = true;
            return;
        }
        int x = AbstractDungeon.miscRng.random(0, 9);
        AbstractPlayer p = AbstractDungeon.player;
        switch (x) {
            case 0:
                applyToSelfTop(new StrengthPower(p, 1));
                break;
            case 1:
                applyToSelfTop(new DexterityPower(p, 1));
                break;
            case 2:
                applyToSelfTop(new MetallicizePower(p, 2));
                break;
            case 3:
                applyToSelfTop(new HundredCutsPower(p, 1));
                break;
            case 4:
                att(new ChannelAction(new Lightning()));
                att(new IncreaseMaxOrbAction(1));
                break;
            case 5:
                att(new ChannelAction(new Frost()));
                att(new IncreaseMaxOrbAction(1));
                break;
            case 6:
                applyToSelfTop(new MantraPower(p, 5));
                break;
            case 7:
                att(new MyAddTempHPAction(p, p, 5));
                break;
            case 8:
                applyToSelfTop(new StoneskinPower(p, 2));
                break;
            case 9:
                applyToSelf(new ForesightPower(p, 1));
                break;
        }
        isDone = true;
        tickDuration();
    }
}
