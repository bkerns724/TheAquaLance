package theExile.orbs;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theExile.ExileMod;
import theExile.util.Wiz;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.*;
import static theExile.util.Wiz.adp;

public class SwarmOfBees extends CustomOrb implements OnAttackOrb {
    public static final String ORB_ID = ExileMod.makeID(SwarmOfBees.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH_O = "exilemodResources/images/vfx/Empty.png";
    private static final String IMG_PATH = "exilemodResources/images/vfx/Bee.png";
    private static final Texture BEE_IMG = ImageMaster.loadImage(IMG_PATH);
    private static final int EVOKE_BONUS = 6;
    private static final int BEE_COUNT = 10;

    private final ArrayList<Bee> bees = new ArrayList<>();

    private class Bee {
        private static final float SPAWN_DISTANCE = 50f;
        private static final float TETHER_DISTANCE = SPAWN_DISTANCE/2f;
        private static final float SPAWN_VELOCITY_VAR = 10f;
        private static final float ACC_VAR = 10f;

        private float x, y;
        private float vx, vy;
        private float ax, ay;
        private float timer;
        private float rotation;

        private Bee() {
            float dist = MathUtils.randomTriangular(0f, SPAWN_DISTANCE);
            float angle = MathUtils.random(0f, MathUtils.PI2);
            this.x = dist * cos(angle);
            this.y = dist * sin(angle);

            float speed = MathUtils.random(-SPAWN_VELOCITY_VAR, SPAWN_VELOCITY_VAR);
            float moveAngle = MathUtils.random(0f, MathUtils.PI2);
            vx = speed * cos(moveAngle);
            vy = speed * sin(moveAngle);

            rotation = atan2(vy, vx);
            generateRandomAcc();
        }

        private void generateRandomAcc() {
            float dist = (float)Math.sqrt(x*x + y*y);
            float angle = MathUtils.atan2(y, x);

            float accAdjX = (TETHER_DISTANCE - dist) * cos(angle);
            float accAdjY = (TETHER_DISTANCE - dist) * sin(angle);

            float acc = MathUtils.random(-ACC_VAR, ACC_VAR);
            float accAngle = MathUtils.random(0f, MathUtils.PI2);
            ax = acc * cos(accAngle) + accAdjX;
            ay = acc * sin(accAngle) + accAdjY;

            if (ax > ACC_VAR)
                ax = ACC_VAR;
            else if (ax < -ACC_VAR)
                ax = -ACC_VAR;

            if (ay > ACC_VAR)
                ay = ACC_VAR;
            else if (ay < -ACC_VAR)
                ay = -ACC_VAR;

            timer = MathUtils.random(0.2f, 1.0f);
        }

        private void update() {
            float delta = Gdx.graphics.getDeltaTime();

            x += vx*delta + ax*delta*delta/2f;
            y += vy*delta + ay*delta*delta/2f;

            vx += ax*delta;
            vy += ay*delta;

            rotation = atan2(vy, vx);
            hb.move(x, y);

            timer -= delta;
            if (timer < 0)
                generateRandomAcc();
        }

        private void render(SpriteBatch sb) {
            sb.setColor(new Color(1, 1, 1, c.a));
            sb.setBlendFunction(770, 771);

            float beeX = x + cX;
            float beeY = y + cY;

            sb.draw(img, beeX - BEE_IMG.getWidth()/2f, beeY - BEE_IMG.getHeight()/2f, BEE_IMG.getWidth(), BEE_IMG.getHeight(),
                    BEE_IMG.getWidth(), BEE_IMG.getHeight(), scale, scale, rotation, 0, 0,
                    BEE_IMG.getWidth(), BEE_IMG.getHeight(), false, false);
        }
    }

    public SwarmOfBees(int passive)
    {
        super(ORB_ID, NAME, passive, 0, "", "", IMG_PATH_O);
        basePassiveAmount = passive;
        baseEvokeAmount = basePassiveAmount + EVOKE_BONUS;
        showEvokeValue = true;

        generateBees();

        applyFocus();
        updateDescription();
    }

    private void generateBees() {
        bees.clear();
        for (int i = 0; i < BEE_COUNT; i++) {
            Bee newBee = new Bee();
            bees.add(newBee);
        }
    }

    public void applyFocus() {
        AbstractPower power = adp().getPower("Focus");
        if (power != null)
            passiveAmount = Math.max(0, basePassiveAmount + power.amount);
        else
            passiveAmount = basePassiveAmount;

        evokeAmount = passiveAmount + EVOKE_BONUS;
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
        long id = CardCrawlGame.sound.play("BEES", 0.1f);
        CardCrawlGame.sound.fadeOut("BEES", id);
    }

    @Override
    public void onAttack(AbstractMonster target,DamageInfo info) {
        Wiz.thornDmgTop(target, passiveAmount, ExileMod.Enums.BEE);
    }

    @Override
    public void onEvoke() {
        AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(true);
        Wiz.thornDmgTop(target, evokeAmount, ExileMod.Enums.BEE);
    }

    @Override
    public void updateAnimation() {
        cX = MathHelper.orbLerpSnap(cX, AbstractDungeon.player.animX + tX);
        cY = MathHelper.orbLerpSnap(cY, AbstractDungeon.player.animY + tY);
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);

        hb.move(cX, cY);

        for (Bee bee : bees)
            bee.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for (Bee bee : bees)
            bee.render(sb);

        renderText(sb);
        hb.render(sb);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + evokeAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new SwarmOfBees(basePassiveAmount);
    }
}