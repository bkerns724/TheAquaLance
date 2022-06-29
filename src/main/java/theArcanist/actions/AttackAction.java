package theArcanist.actions;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theArcanist.ArcanistMod;
import theArcanist.vfx.*;

import java.util.ArrayList;

import static theArcanist.util.Wiz.*;

public class AttackAction extends AbstractGameAction {
    private AbstractMonster m;
    private AttackEffect effect;
    private DamageInfo info;
    private Color color;
    private boolean rainbow;
    private int[] multiDamage;

    public static ArrayList<AbstractGameAction.AttackEffect> simpleEffects;

    public AttackAction(AbstractMonster m, DamageInfo info, AttackEffect effect, Color color, boolean rainbow) {
        this.m = m;
        this.effect = effect;
        this.info = info;
        this.color = color;
        this.rainbow = rainbow;
    }

    public AttackAction(int[] multiDamage, AttackEffect effect, Color color, boolean rainbow) {
        this.multiDamage = multiDamage.clone();
        this.m = null;
        this.info = null;
        this.effect = effect;
        this.color = color;
        this.rainbow = rainbow;
    }

    @Override
    public void update() {
        AbstractGameAction action;

        if (simpleEffects.contains(effect)) {
            if (m != null)
                action = new DamageAction(m, info, effect);
            else
                action = new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, effect);

            ColoredDamagePatch.DamageActionColorField.damageColor.set(action, color);
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

        color = null;
        rainbow = false;

        if (effect == AttackEffect.LIGHTNING) {
            color = Color.YELLOW.cpy();
            if (m == null) {
                att(new SFXAction("ORB_LIGHTNING_EVOKE"));
                forAllMonstersLiving(m ->
                        att(new VFXAction(new LightningEffect(m.drawX, m.drawY))));
            }
            else {
                att(new VFXAction(new LightningEffect(m.drawX, m.drawY)));
                att(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        }

        if (effect == ArcanistMod.Enums.ICE_L) {
            color = Color.BLUE.cpy();
            vfxTop(new BlizzardEffect(50, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.5f);
        }

        if (effect == ArcanistMod.Enums.SOUL_FIRE_M) {
            color = Color.PURPLE.cpy();
            if (m == null)
                forAllMonstersLiving(m ->
                        vfxTop(new SoulBlowEffect(m.hb.cX, m.hb.cY, 3), 0.2f));
            else
                vfxTop(new SoulBlowEffect(m.hb.cX, m.hb.cY, 3), 0.2f);
        }

        if (effect == ArcanistMod.Enums.SOUL_FIRE_L) {
            color = Color.PURPLE.cpy();
            vfxTop(new ScreenOnSoulFireEffect(), 1.0f);
        }

        if (effect == ArcanistMod.Enums.FORCE_L) {
            color = Color.PINK.cpy();
            if (m == null)
                vfxTop(new ForceHammerEffect(), ForceHammerEffect.DUR_BEFORE_IMPACT - 0.1f);
            else
                vfxTop(new ForceHammerEffect(m.hb.cX, m.hb.y), ForceHammerEffect.DUR_BEFORE_IMPACT - 0.1f);
        }

        if (effect == ArcanistMod.Enums.DARK_L) {
            color = Color.BLACK.cpy();
            if (m == null)
                vfxTop(new DarkLargeEffect(null), DarkLargeEffect.DUR_BEFORE_IMPACT - 0.3f);
            else
                vfxTop(new DarkLargeEffect(m), DarkLargeEffect.DUR_BEFORE_IMPACT - 0.3f);
        }

        if (effect == ArcanistMod.Enums.DARK_WAVE) {
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 1.0f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX + m.hb.width/2.0f, 1.0f), 0.5F);
        }

        if (effect == ArcanistMod.Enums.DARK_WAVE_M) {
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 1.5f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX + m.hb.width/2.0f, 1.5f), 0.5F);
        }

        if (effect == ArcanistMod.Enums.DARK_WAVE_L) {
            if (m == null)
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f, 2.25f), 0.5F);
            else
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, m.hb.cX + m.hb.width/2.0f, 2.25f), 0.5F);
        }

        if (effect == ArcanistMod.Enums.BLUNT_MASSIVE) {
            if (m == null) {
                // I super hate how wait action has a maximum of 0.1f on Fast setting
                for (int i = 0; i < 6; i++)
                    att(new WaitAction(0.1f));
                forAllMonstersLiving(m ->
                        vfxTop(new MyWeightyImpactEffect(m.hb.cX, m.hb.cY)));
            }
            else
                vfxTop(new MyWeightyImpactEffect(m.hb.cX, m.hb.cY), 0.6f);
        }

        if (effect == ArcanistMod.Enums.SLASH_MASSIVE) {
            if (m == null) {
                att(new WaitAction(0.1f));
                forAllMonstersLiving(m ->
                        vfxTop(new SlashMassiveEffect(m)));
            }
            else
                vfxTop(new SlashMassiveEffect(m), SlashMassiveEffect.EFFECT_DUR - SlashMassiveEffect.HIT_TIME - 0.1f);
        }

        ColoredDamagePatch.DamageActionColorField.damageColor.set(action, color);
        ColoredDamagePatch.DamageActionColorField.rainbow.set(action, rainbow);
        if (color != null)
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);

        isDone = true;
    }

    static {
        simpleEffects = new ArrayList<>();
        simpleEffects.add(ArcanistMod.Enums.ICE);
        simpleEffects.add(ArcanistMod.Enums.ICE_M);
        simpleEffects.add(ArcanistMod.Enums.SOUL_FIRE);
        simpleEffects.add(ArcanistMod.Enums.FORCE);
        simpleEffects.add(ArcanistMod.Enums.FORCE_M);
        simpleEffects.add(ArcanistMod.Enums.DARK);
        simpleEffects.add(ArcanistMod.Enums.DARK_M);
        simpleEffects.add(ArcanistMod.Enums.RESONANT);
        simpleEffects.add(ArcanistMod.Enums.RESONANT_M);
        simpleEffects.add(ArcanistMod.Enums.RESONANT_L);
        simpleEffects.add(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        simpleEffects.add(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        simpleEffects.add(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        simpleEffects.add(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        simpleEffects.add(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        simpleEffects.add(AbstractGameAction.AttackEffect.SLASH_HEAVY);
        simpleEffects.add(AbstractGameAction.AttackEffect.NONE);
        simpleEffects.add(AbstractGameAction.AttackEffect.FIRE);
        simpleEffects.add(AbstractGameAction.AttackEffect.POISON);
        simpleEffects.add(AbstractGameAction.AttackEffect.SMASH);
        simpleEffects.add(AbstractGameAction.AttackEffect.SHIELD);
    }
}
