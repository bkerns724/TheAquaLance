package theExile.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.vfx.ShortScreenOnFireEffect;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class CataclysmPower extends AbstractExilePower {
    public static String POWER_ID = makeID(CataclysmPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int CARD_COUNT = 12;

    public CataclysmPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
        isTwoAmount = true;
        amount2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % CARD_COUNT;
    }

    @Override
    public void atStartOfTurn() {
        amount2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % CARD_COUNT;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        amount2 = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % CARD_COUNT;
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % CARD_COUNT == 0) {
            vfxTop(new ShortScreenOnFireEffect());
            DamageAllEnemiesAction damageAction = new DamageAllEnemiesAction(adp(), amount,
                    DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(damageAction, Color.FIREBRICK.cpy());
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(damageAction, ColoredDamagePatch.FadeSpeed.SLOW);
            att(damageAction);
        }
    }

    @Override
    public void updateDescription() {
        if (amount2 != 1 && descriptionArray.length > 1)
            description = descriptionArray[1];
        else
            description = descriptionArray[0];
        description = description.replace("!A!", "#b" + amount);
        description = description.replace("!A2!", "#b" + CARD_COUNT);
    }
}