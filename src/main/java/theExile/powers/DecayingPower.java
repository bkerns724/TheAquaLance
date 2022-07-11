package theExile.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.ExileMod;

import static theExile.util.Wiz.atb;

public class DecayingPower extends AbstractExilePower implements HealthBarRenderPower {
    public static String POWER_ID = ExileMod.makeID(DecayingPower.class.getSimpleName());
    public static final Color DECAY_COLOR = new Color(74f/255, 163f/255, 79f/255, 1f);

    public DecayingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        LoseHPAction action = new LoseHPAction(owner, AbstractDungeon.player, amount);
        ColoredDamagePatch.DamageActionColorField.damageColor.set(action, DECAY_COLOR);
        ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
        atb(action);
    }

    @Override
    public int getHealthBarAmount() {
        return amount;
    }

    @Override
    public Color getColor() {
        return DECAY_COLOR;
    }
}