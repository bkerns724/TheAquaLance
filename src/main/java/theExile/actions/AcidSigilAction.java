package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.ExileMod;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.getMonsters;
import static theExile.util.Wiz.*;

public class AcidSigilAction extends AbstractGameAction {
    private AbstractCard card;

    public AcidSigilAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        target = getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);

        if (target != null) {
            card.calculateCardDamage((AbstractMonster) target);
            applyToEnemyTop(target, new VulnerablePower(target, card.magicNumber, false));
            att(new DamageAction(target, new DamageInfo(adp(), card.damage, card.damageTypeForTurn), ExileMod.Enums.ACID));
        }

        isDone = true;
    }
}

