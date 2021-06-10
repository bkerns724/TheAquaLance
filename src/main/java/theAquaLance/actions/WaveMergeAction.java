package theAquaLance.actions;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theAquaLance.cards.AbstractWaveCard;

import java.util.ArrayList;

import static theAquaLance.util.Wiz.*;

public class WaveMergeAction extends AbstractGameAction {
    private AbstractWaveCard card;
    private static final float DURATION = 0.75F;
    private static final Texture GLOW_IMAGE = new Texture("aqualancemodResources/images/vfx/GlowCircle.png");
    private boolean partOneStart = false;
    private boolean partTwoStart = false;
    private ArrayList<AbstractCard> cardsToRemove;

    public WaveMergeAction(AbstractWaveCard card) {
        this.card = card;
        actionType = ActionType.SPECIAL;
        duration = DURATION;
    }

    public void update() {
        if (!partOneStart) {
            cardsToRemove = new ArrayList<>();
            partOneStart = true;
            boolean nothingToDo = true;
            for (AbstractCard c : adp().hand.group) {
                if (c instanceof AbstractWaveCard && ((AbstractWaveCard)c).mergeValue <= card.mergeValue ) {
                    nothingToDo = false;
                    ArrayList<AbstractWaveCard> cardsToRemove2 = new ArrayList<>();
                    for (AbstractWaveCard c2 : ((AbstractWaveCard) c).mergedCards) {
                        card.addCard(c2);
                        cardsToRemove2.add(c2);
                    }
                    for (AbstractWaveCard c2 : cardsToRemove2)
                        ((AbstractWaveCard) c).removeCard(c2);
                    card.addCard((AbstractWaveCard) c);
                    cardsToRemove.add(c);
                }
            }
            for (AbstractCard c : cardsToRemove) {
                c.target_x = Settings.WIDTH/2.0F;
                c.target_y = Settings.HEIGHT/2.0F;
                adp().limbo.addToBottom(c);
                adp().hand.removeCard(c);
            }
            card.calculateBonuses();
            if (nothingToDo)
                isDone = true;
        }

        if (duration < 0.5f && !partTwoStart) {
            partTwoStart = true;

            AbstractGameEffect glowCircleEffect = new VfxBuilder(GLOW_IMAGE, card.hb.cX, card.hb.cY, 0.25f)
                    .scale(0, 2.0f, VfxBuilder.Interpolations.CIRCLEOUT)
                    .andThen(0.25f)
                    .scale(2.0f, 0, VfxBuilder.Interpolations.CIRCLEIN)
                    .build();

            AbstractDungeon.topLevelEffects.add(glowCircleEffect);
        }

        if (duration < 0.25f) {
            for (AbstractCard c : cardsToRemove)
                adp().limbo.removeCard(c);
        }

        tickDuration();
    }
}
