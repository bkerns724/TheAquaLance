package theArcanist.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theArcanist.ArcanistMod;
import theArcanist.actions.PandaSmackAction;

import static java.lang.Math.pow;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.att;

public class CrazyPanda extends CustomOrb {
    public static final String ORB_ID = ArcanistMod.makeID(CrazyPanda.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = "arcanistmodResources/images/vfx/CrazyPanda.png";

    // DO NOT SET EITHER OF THESE TO ZERO
    private static final float BOUNCE_DURATION = 1.0f;
    private static final float GRAVITY = 2700.0f;

    private float bounceTime = 0;
    private boolean shooting = false;
    private boolean bouncing = false;
    private float bounceSourceX;
    private float bounceSourceY;
    private float peakY;
    private float peakTime;

    private float rotation;

    public CrazyPanda(int passive)
    {
        super(ORB_ID, NAME, passive, 0, "", "", IMG_PATH);
        showEvokeValue = false;
        rotation = 0.0f;
        updateDescription();
    }

    @Override
    public void onEvoke() {
        att(new DecreaseMaxOrbAction(1));
    }

    @Override
    public void playChannelSFX() {
    }

    @Override
    public void onStartOfTurn() {
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
        bobEffect.update();
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
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - 48.0F, cY - 48.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale, scale, rotation, 0, 0, 96, 96, false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        if (!shooting)
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount),
                    cX + NUM_X_OFFSET + 25*Settings.scale, cY + NUM_Y_OFFSET - 25* Settings.yScale,
                    new Color(1.0f, 0.25f, 0.25f, 1.0f), fontScale);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new CrazyPanda(passiveAmount);
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