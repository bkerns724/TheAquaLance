package theAquaLance.actions;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.util.Wiz.*;

public class EmbedAction extends AbstractGameAction {
    private AbstractMonster m;
    private AbstractEmbedCard c;
    private static final float DURATION = 1.2F;
    private static final Texture SHARD_IMAGE = new Texture("aqualancemodResources/images/vfx/IceShard.png");
    private static final Texture GLOW_IMAGE = new Texture("aqualancemodResources/images/vfx/GlowCircle.png");
    private AbstractGameEffect icicleEffect;
    private boolean partOneStart = false;
    private boolean partTwoStart = false;

    public EmbedAction(AbstractEmbedCard c, AbstractMonster target) {
        this.c = c;
        this.m = target;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        if (m == null) {
            c.missedMonsters = true;
            isDone = true;
        }

        if (duration < 0.95f && !partOneStart) {
            partOneStart = true;
            float dy = m.hb.cY - c.hb.cY;
            float dx = m.hb.cX - c.hb.cX;
            float iceAngle = MathUtils.atan2(dy, dx)*180.0f/(float)Math.PI;

            AbstractGameEffect glowCircleEffect = new VfxBuilder(GLOW_IMAGE, c.hb.cX, c.hb.cY, 0.25f)
                    .scale(0, 2.0f, VfxBuilder.Interpolations.CIRCLEOUT)
                    .andThen(0.25f)
                    .scale(2.0f, 0, VfxBuilder.Interpolations.CIRCLEIN)
                    .build();

            icicleEffect = new VfxBuilder(SHARD_IMAGE, c.hb.cX, c.hb.cY, 0.25f)
                    .setAngle(iceAngle)
                    .andThen(0.25f)
                    .moveX(c.hb.cX, m.hb.cX, VfxBuilder.Interpolations.LINEAR)
                    .moveY(c.hb.cY, m.hb.cY, VfxBuilder.Interpolations.LINEAR)
                    .andThen(0.2f)
                    .fadeOut(0.2f)
                    .build();

            AbstractDungeon.topLevelEffects.add(glowCircleEffect);
        }

        if (duration < 0.7f && !partTwoStart) {
            if (adp().limbo.contains(c))
                adp().limbo.removeCard(c);
            adp().cardInUse = null;
            AbstractDungeon.effectList.add(icicleEffect);
            partTwoStart = true;
            att(new DamageAction(m, new DamageInfo(adp(), c.damage, c.damageTypeForTurn)));
            if (m.hasPower(ArtifactPower.POWER_ID)) {
                c.hitArtifact = true;
                c.current_y = m.hb_y;
                c.current_x = m.hb_x;
            }
            applyToEnemyTop(m, new EmbedPower(m, c));
        }

        tickDuration();
    }
}
