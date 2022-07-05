package theArcanist.relics;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theArcanist.TheArcanist;
import theArcanist.damagemods.SoulFireDamage;
import theArcanist.vfx.SoulBlowEffect;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;
import static theArcanist.util.Wiz.vfxTop;

public class SoulLamp extends AbstractArcanistRelic {
    public static final String ID = makeID(SoulLamp.class.getSimpleName());
    public static final int REVENGE_AMOUNT = 15;
    private boolean activated;

    public SoulLamp() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = REVENGE_AMOUNT;
        activated = false;
        setUpdatedDescription();
    }

    @Override
    public void atTurnStart() {
        activated = false;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != adp() && !activated) {
            activated = true;
            flash();
            DamageInfo newInfo = new DamageInfo(adp(), REVENGE_AMOUNT, DamageInfo.DamageType.THORNS);
            ArrayList<AbstractDamageModifier> damageList = new ArrayList<>();
            damageList.add(new SoulFireDamage());
            DamageModifierManager.bindDamageMods(newInfo, damageList);
            DamageAction action = new DamageAction(info.owner, newInfo, AbstractGameAction.AttackEffect.NONE);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(action, Color.PURPLE.cpy());
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
            att(action);
            vfxTop(new SoulBlowEffect(info.owner.hb.cX, info.owner.hb.cY, 3), 0.2f);
            att(new RelicAboveCreatureAction(info.owner, this));
        }
        return damageAmount;
    }
}
