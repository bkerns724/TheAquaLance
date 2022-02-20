package theArcanist.actions;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class PewcumberAction extends AbstractGameAction {
    private AbstractMonster m;
    private static final float DURATION = 1F;
    private static final Texture CUCUMBER_IMAGE =
            new Texture("arcanistmodResources/imgage/vfx/Cucumber.png");
    private AbstractPlayer p = AbstractDungeon.player;
    private boolean thunkEffect;

    public PewcumberAction(AbstractMonster monster, int amount) {
        this.m = monster;
        this.amount = amount;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
        thunkEffect = false;
    }

    public void update() {
        if (m == null) {
            isDone = true;
            return;
        }

        float targetX = 0f;
        float targetY = 0f;
        if (duration == DURATION) {
            targetX = m.hb.cX + AbstractDungeon.miscRng.random(-25.0f*Settings.xScale, 25.0f*Settings.xScale);
            targetY = m.hb.cY + AbstractDungeon.miscRng.random(-25.0f*Settings.yScale, 25.0f*Settings.yScale);
            float targetX2 = targetX + AbstractDungeon.miscRng.random(-400.0f*Settings.xScale, 400.0f*Settings.xScale);
            float targetY2 = targetY + AbstractDungeon.miscRng.random(-400.0f*Settings.yScale, 400.0f*Settings.yScale);
            AbstractGameEffect cucumberEffect = new VfxBuilder(CUCUMBER_IMAGE, adp().hb.cX, adp().hb.cY, 0.5f)
                    .moveX(adp().hb.cX, targetX, VfxBuilder.Interpolations.LINEAR)
                    .moveY(adp().hb.cY, targetY, VfxBuilder.Interpolations.LINEAR)
                    .rotate(720.0f)
                    .andThen(0.5f)
                    .moveX(targetX, targetX2, VfxBuilder.Interpolations.BOUNCE)
                    .moveY(targetY, targetY2, VfxBuilder.Interpolations.BOUNCE)
                    .rotate(360.0f)
                    .fadeOut(0.5f)
                    .build();

            AbstractDungeon.topLevelEffects.add(cucumberEffect);
            CardCrawlGame.sound.play(ArcanistMod.PEW_KEY);
        }

        if (duration <= DURATION - 0.5f && !thunkEffect) {
            thunkEffect = true;
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(targetX, targetY, AttackEffect.BLUNT_HEAVY));
            if (p != null && p.currentHealth > 0) {
                m.damage(new DamageInfo(adp(), amount, DamageInfo.DamageType.THORNS));
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
            }
        }

        tickDuration();
    }
}
