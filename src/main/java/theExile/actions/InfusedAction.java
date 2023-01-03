package theExile.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theExile.cards.AbstractExileCard;
import theExile.cards.AbstractExileCard.elenum;

import java.util.ArrayList;
import java.util.Arrays;

import static theExile.util.Wiz.adp;

public class InfusedAction extends AbstractGameAction {
    private final static float DURATION = 0.25f;

    public InfusedAction() {
        duration = startDuration = DURATION;
        actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        for (AbstractCard card : adp().hand.group) {
            if (!(card instanceof AbstractExileCard) || card.type != AbstractCard.CardType.ATTACK )
                continue;
            ArrayList<elenum> list = new ArrayList<>(Arrays.asList(elenum.ELDRITCH, elenum.FORCE, elenum.ICE, elenum.LIGHTNING));
            for (elenum e : ((AbstractExileCard) card).damageModList)
                list.remove(e);

            if (list.size() == 0)
                continue;

            int x = MathUtils.random(0, list.size() - 1);
            ((AbstractExileCard) card).addModifier(list.get(x));
        }
        isDone = true;
    }
}
