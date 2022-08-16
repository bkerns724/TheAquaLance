package theExile.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theExile.ExileMod;
import theExile.actions.RingingLoseHpAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.atb;

public class RingingPower extends AbstractExilePower implements HealthBarRenderPower {
    public static String POWER_ID = makeID(RingingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RingingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn() {
        if (adRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flashWithoutSound();
            atb(new RingingLoseHpAction(owner, amount, ExileMod.Enums.RESONANT));
        }
    }

    @Override
    public int getHealthBarAmount() {
        int output = amount;
        CrumblingPower corPow = (CrumblingPower) owner.getPower(CrumblingPower.POWER_ID);
        DecayPower decayPow = (DecayPower) owner.getPower(DecayPower.POWER_ID);
        if (corPow != null)
            output *= (1f + corPow.amount/100f);
        if (decayPow != null)
            output += decayPow.amount;
        return output;
    }

    @Override
    public Color getColor() {
        return Color.YELLOW.cpy();
    }
}