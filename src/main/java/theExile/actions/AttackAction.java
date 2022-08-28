package theExile.actions;

import com.badlogic.gdx.graphics.Color;
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
import com.megacrit.cardcrawl.vfx.combat.*;
import theExile.ExileMod;
import theExile.vfx.*;

import java.util.ArrayList;

import static theExile.util.Wiz.*;

public class AttackAction extends AbstractGameAction {
    private final AbstractMonster m;
    private final AttackEffect effect;
    private final DamageInfo info;
    private int[] multiDamage;

    public static ArrayList<AttackEffect> simpleEffects;

    public AttackAction(AbstractMonster m, DamageInfo info, AttackEffect effect) {
        this.m = m;
        this.effect = effect;
        this.info = info;
    }

    public AttackAction(int[] multiDamage, AttackEffect effect) {
        this.multiDamage = multiDamage.clone();
        this.m = null;
        this.info = null;
        this.effect = effect;
    }

    @Override
    public void update() {
        AbstractGameAction action;
        Color color = null;
        boolean rainbow = false;

        if (simpleEffects.contains(effect)) {
            if (m != null)
                action = new DamageAction(m, info, effect);
            else
                action = new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, effect);

            if (effect == ExileMod.Enums.ICE || effect == ExileMod.Enums.ICE_M)
                color = Color.BLUE.cpy();
            else if (effect == ExileMod.Enums.FORCE || effect == ExileMod.Enums.FORCE_M)
                color = Color.PINK.cpy();
            else if (effect == ExileMod.Enums.ELDRITCH || effect == ExileMod.Enums.ELDRITCH_M)
                color = Color.BLACK.cpy();
            else if (effect == ExileMod.Enums.LIGHTNING_M || effect == ExileMod.Enums.LIGHTNING_L)
                color = Color.GREEN.cpy();
            else if (effect == AttackEffect.FIRE)
                color = Color.ORANGE.cpy();

            if (color != null) {
                ColoredDamagePatch.DamageActionColorField.damageColor.set(action, color);
                ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
            }
            ColoredDamagePatch.DamageActionColorField.rainbow.set(action, rainbow);

            att(action);
            isDone = true;
            return;
        }

        if (m != null)
            action = new DamageAction(m, info, AttackEffect.NONE);
        else
            action = new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE);

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
            color = Color.BLACK.cpy();
            if (m == null)
                vfxTop(new DarkLargeEffect(null), DarkLargeEffect.DUR_BEFORE_IMPACT - 0.3f);
            else
                vfxTop(new DarkLargeEffect(m), DarkLargeEffect.DUR_BEFORE_IMPACT - 0.3f);
        }

        if (effect == ExileMod.Enums.DARK_WAVE) {
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 1.0f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX, 1.0f), 0.5F);
        }

        if (effect == ExileMod.Enums.DARK_WAVE_M) {
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 1.5f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX, 1.5f), 0.5F);
        }

        if (effect == ExileMod.Enums.DARK_WAVE_L) {
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 2.25f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX, 2.25f), 0.5F);
        }

        if (effect == ExileMod.Enums.BLUNT_MASSIVE) {
            if (m == null)
                forAllMonstersLiving(m -> vfxTop(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
            else
                vfxTop(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F));
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
            color = Color.ORANGE.cpy();
            if (m == null)
                forAllMonstersLiving(m -> vfxTop(new SearingBlowEffect(m.hb.cX, m.hb.cY, 3), 0.2f));
            else
                vfxTop(new SearingBlowEffect(m.hb.cX, m.hb.cY, 3), 0.2F);
        }

        if (effect == ExileMod.Enums.FIRE_L) {
            color = Color.ORANGE.cpy();
            vfxTop(new ShortScreenOnFireEffect());
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
