package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.SackOfDebris;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.discardTop;

public class SackOfDebrisAction extends AbstractGameAction {
    float startingDuration;
    SackOfDebris card;

    public SackOfDebrisAction(SackOfDebris card) {
        target = adp();
        actionType = ActionType.DAMAGE;
        duration = Settings.ACTION_DUR_FAST;
        startingDuration = duration;
        this.card = card;
    }

    public void update() {
        if (duration == startingDuration) {
            int count = adp().hand.size();
            AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster();
            card.calculateCardDamage(m);
            card.dmgTop(m, card.getSlashEffect());
            discardTop(count, true);

            isDone = true;
        }
    }
}