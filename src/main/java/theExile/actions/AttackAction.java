package theExile.actions;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.patches.BindingPatches;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import theExile.ExileMod;
import theExile.vfx.*;

import java.util.ArrayList;

import static theExile.util.Wiz.*;

public class AttackAction extends AbstractGameAction {
    private final AbstractMonster m;
    private final AttackEffect effect;
    private AbstractGameAction action;

    public static ArrayList<AttackEffect> simpleEffects;
    private static final Color ELDRITCH_COLOR = new Color(0.35f, 0f, 0.35f, 1f);

    public AttackAction(AbstractMonster m, DamageInfo info, AttackEffect effect) {
        this.m = m;
        this.effect = effect;
        createAction(m, info, effect);
        bindAction();
    }

    public AttackAction(int[] multiDamage, AttackEffect effect) {
        this.m = null;
        this.effect = effect;
        createAction(multiDamage, effect);
        bindAction();
    }

    private void createAction(AbstractMonster m, DamageInfo info, AttackEffect effect) {
        if (simpleEffects.contains(effect))
            action = new DamageAction(m, info, effect);
        else if (effect == ExileMod.Enums.BLUNT_MASSIVE)
            action = new DamageAction(m, info, AttackEffect.BLUNT_HEAVY);
        else
            action = new DamageAction(m, info, AttackEffect.NONE);
    }

    private void createAction(int[] multiDamage, AttackEffect effect) {
        if (simpleEffects.contains(effect))
            action = new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, effect);
        else if (effect == ExileMod.Enums.BLUNT_MASSIVE)
            action = new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.BLUNT_HEAVY);
        else
            action = new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE);
    }

    private void bindAction() {
        AbstractGameAction curAction = AbstractDungeon.actionManager.currentAction;
        if (AbstractDungeon.actionManager.currentAction != null) {
            ArrayList<AbstractDamageModifier> list = BindingPatches.BoundGameActionFields.actionDelayedDamageMods.get(curAction);
            BindingHelper.bindAction(list, action);
        }
    }

    @Override
    public void update() {
        Color color = null;
        boolean rainbow = false;

        if (simpleEffects.contains(effect)) {
            if (effect == ExileMod.Enums.ICE || effect == ExileMod.Enums.ICE_M)
                color = Color.BLUE.cpy();
            else if (effect == ExileMod.Enums.FORCE || effect == ExileMod.Enums.FORCE_M)
                color = Color.PINK.cpy();
            else if (effect == ExileMod.Enums.ELDRITCH || effect == ExileMod.Enums.ELDRITCH_M)
                color = ELDRITCH_COLOR.cpy();
            else if (effect == ExileMod.Enums.LIGHTNING_M || effect == ExileMod.Enums.LIGHTNING_L)
                color = Color.GREEN.cpy();
            else if (effect == AttackEffect.FIRE)
                color = Color.FIREBRICK.cpy();
            else if (effect == ExileMod.Enums.ACID || effect == ExileMod.Enums.ACID_M)
                color = Color.FOREST.cpy();
            else if (effect == ExileMod.Enums.BEE)
                color = Color.YELLOW.cpy();

            if (color != null) {
                ColoredDamagePatch.DamageActionColorField.damageColor.set(action, color);
                ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
            }
            ColoredDamagePatch.DamageActionColorField.rainbow.set(action, rainbow);

            att(action);
            isDone = true;
            return;
        }

        att(action);

        if (effect == AttackEffect.LIGHTNING) {
            color = Color.GREEN.cpy();
            if (m == null) {
                att(new SFXAction("ORB_LIGHTNING_EVOKE"));
                forAllMonstersLiving(m ->
                        att(new VFXAction(new LightningEffect(m.drawX, m.drawY))));
            } else {
                att(new VFXAction(new LightningEffect(m.drawX, m.drawY)));
                att(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        }

        if (effect == ExileMod.Enums.ICE_L) {
            color = Color.BLUE.cpy();
            vfxTop(new BlizzardEffect(50, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.5f);
        }

        if (effect == ExileMod.Enums.FORCE_L) {
            color = Color.PINK.cpy();
            if (m == null)
                vfxTop(new ForceHammerEffect(), ForceHammerEffect.DUR_BEFORE_IMPACT - 0.1f);
            else
                vfxTop(new ForceHammerEffect(m.hb.cX, m.hb.y), ForceHammerEffect.DUR_BEFORE_IMPACT - 0.1f);
        }

        if (effect == ExileMod.Enums.ELDRITCH_L) {
            color = ELDRITCH_COLOR.cpy();
            if (m == null)
                vfxTop(new DarkLargeEffect(null), DarkLargeEffect.DUR_BEFORE_IMPACT - 0.3f);
            else
                vfxTop(new DarkLargeEffect(m), DarkLargeEffect.DUR_BEFORE_IMPACT - 0.3f);
        }

        if (effect == ExileMod.Enums.DARK_WAVE) {
            rainbow = true;
            color = null;
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 1.0f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX, 1.0f), 0.5F);
        }

        if (effect == ExileMod.Enums.DARK_WAVE_M) {
            rainbow = true;
            color = null;
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 1.5f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX, 1.5f), 0.5F);
        }

        if (effect == ExileMod.Enums.DARK_WAVE_L) {
            rainbow = true;
            color = null;
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 2.25f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX, 2.25f), 0.5F);
        }

        if (effect == ExileMod.Enums.BLUNT_MASSIVE) {
            if (m == null)
                forAllMonstersLiving(m -> vfxTop(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F,
                        m.hb.cY - m.hb.height / 4.0F)));
            else
                vfxTop(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F,m.hb.cY - m.hb.height / 4.0F));
        }

        if (effect == ExileMod.Enums.SLASH_MASSIVE) {
            if (m == null)
                forAllMonstersLiving(m -> vfxTop(new SlashMassiveEffect(m)));
            else
                vfxTop(new SlashMassiveEffect(m));
        }

        if (effect == ExileMod.Enums.LIGHTNING_M) {
            color = Color.GREEN.cpy();
            if (m == null)
                forAllMonstersLiving(m -> vfxTop(new GreenLightningEffect(m.drawX, m.drawY)));
            else
                vfxTop(new GreenLightningEffect(m.drawX, m.drawY));
        }

        if (effect == ExileMod.Enums.LIGHTNING_L) {
            color = Color.GREEN.cpy();
            if (m == null)
                forAllMonstersLiving(m -> vfxTop(new LargeGreenLightningEffect(m)));
            else
                vfxTop(new LargeGreenLightningEffect(m));
        }

        if (effect == ExileMod.Enums.FIRE_M) {
            color = Color.FIREBRICK.cpy();
            if (m == null)
                forAllMonstersLiving(m -> vfxTop(new SearingBlowEffect(m.hb.cX, m.hb.cY, 3), 0.2f));
            else
                vfxTop(new SearingBlowEffect(m.hb.cX, m.hb.cY, 3), 0.2F);
        }

        if (effect == ExileMod.Enums.FIRE_L) {
            color = Color.FIREBRICK.cpy();
            vfxTop(new ShortScreenOnFireEffect());
        }

        if (effect == ExileMod.Enums.ACID_L) {
            color = Color.FOREST.cpy();
            if (m == null) {
                for (int i = 0; i < 4; i++)
                    att(new AcidSplashAction(true));
            } else {
                for (int i = 0; i < 4; i++)
                    att(new AcidSplashAction(m.hb.cX, m.hb.cY));
            }
        }

        ColoredDamagePatch.DamageActionColorField.rainbow.set(action, rainbow);
        if (color != null) {
            ColoredDamagePatch.DamageActionColorField.damageColor.set(action, color);
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
        }

        isDone = true;
    }

    static {
        simpleEffects = new ArrayList<>();
        simpleEffects.add(ExileMod.Enums.ACID);
        simpleEffects.add(ExileMod.Enums.ACID_M);
        simpleEffects.add(ExileMod.Enums.BLOOD);
        simpleEffects.add(ExileMod.Enums.ICE);
        simpleEffects.add(ExileMod.Enums.ICE_M);
        simpleEffects.add(ExileMod.Enums.FORCE);
        simpleEffects.add(ExileMod.Enums.FORCE_M);
        simpleEffects.add(ExileMod.Enums.ELDRITCH);
        simpleEffects.add(ExileMod.Enums.ELDRITCH_M);
        simpleEffects.add(ExileMod.Enums.RESONANT);
        simpleEffects.add(ExileMod.Enums.RESONANT_M);
        simpleEffects.add(ExileMod.Enums.RESONANT_L);
        simpleEffects.add(ExileMod.Enums.LIGHTNING_S);
        simpleEffects.add(ExileMod.Enums.BEE);
        simpleEffects.add(AttackEffect.BLUNT_LIGHT);
        simpleEffects.add(AttackEffect.BLUNT_HEAVY);
        simpleEffects.add(AttackEffect.SLASH_DIAGONAL);
        simpleEffects.add(AttackEffect.SLASH_VERTICAL);
        simpleEffects.add(AttackEffect.SLASH_HORIZONTAL);
        simpleEffects.add(AttackEffect.SLASH_HEAVY);
        simpleEffects.add(AttackEffect.NONE);
        simpleEffects.add(AttackEffect.FIRE);
        simpleEffects.add(AttackEffect.POISON);
        simpleEffects.add(AttackEffect.SMASH);
        simpleEffects.add(AttackEffect.SHIELD);
        simpleEffects.add(AttackEffect.LIGHTNING);
    }
}
