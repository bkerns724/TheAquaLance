package theExile.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;
import theExile.actions.PandaEvokeAction;
import theExile.actions.PandaSmackAction;
import theExile.patches.PandaPatch;

import static java.lang.Math.pow;
import static theExile.util.Wiz.*;

public class CrazyPanda extends CustomOrb {
    public static final String ORB_ID = ExileMod.makeID(CrazyPanda.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = "exilemodResources/images/vfx/CrazyPanda.png";
    private static final float PANDA_WIDTH = 96.0f;
    private static final Color TEXT_COLOR = new Color(1.0f, 0.25f, 0.25f, 1.0f);

    public static final float BOUNCE_DURATION = 1.0f;
    public static final float GRAVITY = 2700.0f;

    private static final int PASSIVE_AMOUNT = 6;

    private float bounceTime = 0;
    private boolean shooting = false;
    private boolean bouncing = false;
    private float bounceSourceX;
    private float bounceSourceY;
    private float peakY;
    private float peakTime;

    private float rotation;

    public boolean isCopy = false;

    public CrazyPanda()
    {
        super(ORB_ID, NAME, PASSIVE_AMOUNT, PASSIVE_AMOUNT, "", "", IMG_PATH);
        basePassiveAmount = PASSIVE_AMOUNT;
        baseEvokeAmount = PASSIVE_AMOUNT;
        showEvokeValue = false;
        rotation = 0.0f;
        applyFocus();
        updateDescription();
    }

    public void applyFocus() {
        AbstractPower power = adp().getPower("Focus");

        if (power != null)
            passiveAmount = Math.max(0, basePassiveAmount + power.amount);
        else
            passiveAmount = basePassiveAmount;

        if (power != null)
            evokeAmount = Math.max(0, baseEvokeAmount + power.amount);
        else
            evokeAmount = baseEvokeAmount;
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1f);
    }

    @Override
    public void onEvoke() {
        CrazyPanda copy = (CrazyPanda) makeCopy();
        copy.isCopy = true;
        att(new PandaEvokeAction(copy, this));
    }

    @Override
    public void onEndOfTurn() {
        atb(new PandaSmackAction(this));
    }

    public void startShoot() {
        shooting = true;
        bouncing = false;
    }

    public void endShoot() {
        shooting = false;
        bouncing = false;
    }

    public void startBounce(float sourceX, float sourceY) {
        bounceTime = 0;
        bouncing = true;
        bounceSourceX = sourceX;
        bounceSourceY = sourceY;
        calculatePeak();
    }

    private void calculatePeak() {
        peakTime = (tY - bounceSourceY) / BOUNCE_DURATION / GRAVITY + BOUNCE_DURATION / 2;
        peakY = GRAVITY * peakTime * peakTime / 2.0f + bounceSourceY;
    }

    @Override
    public void updateAnimation() {
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }
        if (shooting)
            rotation += 6*Gdx.graphics.getDeltaTime()*360.0f;
        else
            rotation += 0.5f*Gdx.graphics.getDeltaTime()*360.0f;

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);
        if (shooting) {
            if (bouncing) {
                bounceTime += Gdx.graphics.getDeltaTime();
                if (bounceTime > BOUNCE_DURATION) {
                    cX = tX;
                    cY = tY;
                    endShoot();
                } else {
                    cX = bounceSourceX + (tX - bounceSourceX) * (bounceTime / BOUNCE_DURATION);
                    cY = (float)(peakY - pow(bounceTime - peakTime, 2)*GRAVITY/2.0f);
                }
            }
        }
        else {
            cX = MathHelper.orbLerpSnap(cX, tX);
            cY = MathHelper.orbLerpSnap(cY, tY);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (PandaPatch.AbstractOrbIsInPlayerRender.isPlayerRender.get(this))
            return;
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - PANDA_WIDTH/2F, cY - PANDA_WIDTH/2F, PANDA_WIDTH/2F, PANDA_WIDTH/2F,
                PANDA_WIDTH, PANDA_WIDTH, scale, scale, rotation, 0, 0, (int)PANDA_WIDTH, (int)PANDA_WIDTH,
                false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        if (!shooting)
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount),
                    cX + NUM_X_OFFSET + 25*Settings.scale, cY + NUM_Y_OFFSET - 25* Settings.yScale,
                    TEXT_COLOR, fontScale);
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + passiveAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy() {
        CrazyPanda copy = new CrazyPanda();
        copy.cX = cX;
        copy.cY = cY;
        copy.tX = tX;
        copy.tY = tY;
        copy.hb.move(tX, tY);
        copy.rotation = rotation;
        copy.c.a = 1;
        copy.scale = Settings.scale;
        channelAnimTimer = 0;
        return copy;
    }
}

/*
    ym - ys = xm*xm*a/2
    ym - yt = (dur - xm)^2 *a/2
    ym = xm*xm*a/2 + ys
    xm*xm*a/2 + ys - yt = (dur - xm)^2 *a/2 = [dur^2 -2dur*xm + xm^2]*a/2
    ys - yt = [dur^2 - 2dur*xm]*a/2
    ys - yt - dur^2 *a/2 = -dur*xm * a
    [ys - yt - dur^2 * a/2] / -dur*a = xm
    xm = (yt - ys)/dur/a + dur/2
 */