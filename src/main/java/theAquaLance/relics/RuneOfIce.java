package theAquaLance.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static theAquaLance.AquaLanceMod.*;
import static theAquaLance.util.Wiz.*;

public class RuneOfIce extends CardPreviewRelic {
    public static final String ID = makeID("RuneOfIce");
    public static final String IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + ".png");
    public static final String OUTLINE_IMG_PATH = makeRelicPath(ID.replace(modID + ":", "") + "_outline.png");
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static final int VIGOR_AMT = 2;

    public RuneOfIce() {
        super(ID, new Texture(IMG_PATH), new Texture(OUTLINE_IMG_PATH), TIER, SOUND);
        tips.clear();
        tips.add(new PowerTip(name, flavorText));
    }

    public void onEquip() {
        counter = 0;
    }

    public void atTurnStart() {
        counter = 1;
    }


    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target != adp() && counter > 0) {
            counter = 0;
            applyToSelf(new VigorPower(adp(), VIGOR_AMT));
        }
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + VIGOR_AMT + DESCRIPTIONS[1];
    }
}
