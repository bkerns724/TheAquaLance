package theExile.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theExile.cards.AbstractExileCard;
import theExile.cards.EnchantedDagger;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.att;

public class SiphonAction extends AbstractGameAction {
    private final int block;
    private static final float DURATION = Settings.ACTION_DUR_FAST;

    public SiphonAction(int block) {
        duration = DURATION;
        this.block = block;
    }

    @Override
    public void update() {
        if (duration == DURATION) {
            int count = 0;
            for (AbstractCard c : adp().hand.group)
                if (c instanceof AbstractExileCard && c.type == AbstractCard.CardType.ATTACK) {
                    count += ((AbstractExileCard) c).damageModList.size();
                    ((AbstractExileCard) c).damageModList.clear();
                    if (c instanceof EnchantedDagger)
                        ((EnchantedDagger) c).stableList.clear();
                    DamageModifierManager.clearModifiers(c);
                }

            for (int i = 0; i < count; i++)
                att(new GainBlockAction(adp(), block));
        }

        isDone = true;
    }
}
