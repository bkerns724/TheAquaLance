package theArcanist.actions;

import com.badlogic.gdx.graphics.Color;
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

    private static final int ICE_L_FROST_COUNT = 50;
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
        if (simpleEffects.contains(effect)) {
            if (m == null)
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, effect));
            else
                att(new DamageAction(m, info, effect));
        }

        if (effect == ArcanistMod.Enums.DARK_WAVE) {
            if (m == null) {
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
                vfxTop(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f), 0.5F);
            }
            else {
                att(new DamageAction(m, info, AttackEffect.NONE));
                vfx(new DarkWaveEffect(adp().hb.cX, adp().hb.cY, Settings.WIDTH * 1.05f), 0.5F);
            }
            return;
        }

        if (effect == AttackEffect.LIGHTNING) {
            if (m == null) {
                att(new SFXAction("ORB_LIGHTNING_EVOKE"));
                forAllMonstersLiving(m ->
                        att(new VFXAction(new LightningEffect(m.drawX, m.drawY))));
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
            }
            else {
                att(new DamageAction(m, info, AttackEffect.NONE));
                att(new VFXAction(new LightningEffect(m.drawX, m.drawY)));
                att(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        }

        if (effect == ArcanistMod.Enums.ICE_L) {
            if (m == null)
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
            else
                att(new DamageAction(m, info, AttackEffect.NONE));
            vfxTop(new BlizzardEffect(ICE_L_FROST_COUNT, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.5f);
        }

        if (effect == ArcanistMod.Enums.SOUL_FIRE_M) {
            if (m == null) {
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
                forAllMonstersLiving(m ->
                        vfxTop(new SoulExplosionSmallEffect(m.hb.cX, m.hb.cY)));
            } else {
                att(new DamageAction(m, info, AttackEffect.NONE));
                vfxTop(new SoulExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1f);
            }
        }

        if (effect == ArcanistMod.Enums.SOUL_FIRE_L) {
            if (m == null)
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
            else
                att(new DamageAction(m, info, AttackEffect.NONE));
            vfxTop(new ScreenOnSoulFireEffect(), 1.0f);
        }

        if (effect == ArcanistMod.Enums.FORCE_L) {
            if (m == null) {
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
                vfxTop(new ForceHammerEffect(), ForceHammerEffect.DUR_BEFORE_IMPACT);
            } else {
                att(new DamageAction(m, info, AttackEffect.NONE));
                vfxTop(new ForceHammerEffect(m.hb.cX, m.hb.y), ForceHammerEffect.DUR_BEFORE_IMPACT);
            }
        }

        if (effect == ArcanistMod.Enums.DARK_COIL_M) {
            if (m == null) {
                att(new DamageAllEnemiesAction(adp(), multiDamage, DamageInfo.DamageType.NORMAL, AttackEffect.NONE));
                att(new WaitAction(0.1f));
                att(new WaitAction(0.1f));
                att(new WaitAction(0.1f));
                att(new WaitAction(0.1f));
                forAllMonstersLiving(m ->
                        vfxTop(new DarkSphereEffect(m.hb.cX, m.hb.cY)));
            } else {
                att(new DamageAction(m, info, AttackEffect.NONE));
                vfxTop(new DarkSphereEffect(m.hb.cX, m.hb.cY), 0.4f);
            }
        }

        isDone = true;
    }

    static {
        simpleEffects = new ArrayList<>();
        simpleEffects.add(ArcanistMod.Enums.ICE);
        simpleEffects.add(ArcanistMod.Enums.ICE_M);
        simpleEffects.add(ArcanistMod.Enums.SOUL_FIRE);
        simpleEffects.add(ArcanistMod.Enums.FORCE);
        simpleEffects.add(ArcanistMod.Enums.FORCE_M);
        simpleEffects.add(ArcanistMod.Enums.DARK_COIL);
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
