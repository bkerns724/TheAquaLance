package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.AbstractExileCard;
import theExile.powers.AcousticsPower;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.forAllMonstersLiving;

public class ResonanceUseCardAction extends AbstractGameAction {
    private final AbstractExileCard card;
    private final boolean acoustic;

    public ResonanceUseCardAction(AbstractExileCard card, AbstractMonster target) {
        this.card = card;
        this.target = target;
        acoustic = adp() != null && adp().hasPower(AcousticsPower.POWER_ID);
    }

    @Override
    public void update() {
        isDone = true;
        if (!acoustic && target == null) {
            card.calculateCardDamage(null);
            card.nonTargetUse();
            card.autoTargetUse(card.getTarget());
        }
        else if (!acoustic) {
            if (!card.canUse(adp(), (AbstractMonster) target))
                return;
            card.calculateCardDamage((AbstractMonster) target);
            card.singleTargetUse((AbstractMonster) target);
            card.autoTargetUse(card.getTarget());
            card.calculateCardDamage(null);
            card.nonTargetUse();
        } else {
            forAllMonstersLiving(mon -> {
                if (card.canUse(adp(), mon)) {
                    card.calculateCardDamage(mon);
                    card.singleTargetUse(mon);
                    card.autoTargetUse(mon);
                }
            });
            card.calculateCardDamage(null);
            card.nonTargetUse();
        }
    }
}
