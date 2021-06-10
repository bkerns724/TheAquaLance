package theAquaLance.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.AbstractWaveCard;

import static theAquaLance.util.Wiz.*;

public class WaveAction extends AbstractGameAction {
    private AbstractWaveCard card;
    private static final float DURATION = 0.1F;

    public WaveAction(AbstractWaveCard c, AbstractMonster m) {
        card = c;
        target = m;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        card.applyPowers();
        card.calculateCardDamage((AbstractMonster) target);
        if (card.baseBlock > 0)
            att(new GainBlockAction(adp(), card.block));
        if (!card.areaAttack) {
            att(new DamageAction(target, new DamageInfo(adp(), card.damage, card.damageTypeForTurn), AquaLanceMod.Enums.WATER));
            if (card.magicNumber > 0)
                applyToEnemyTop((AbstractMonster) target, new MarkPower(target, card.magicNumber));
        }
        else {
            att(new DamageAllEnemiesAction(adp(), card.multiDamage, DamageInfo.DamageType.NORMAL,
                    AquaLanceMod.Enums.WATER));
            if (card.magicNumber > 0)
                forAllMonstersLiving(m -> applyToEnemyTop(m, new MarkPower(m, card.magicNumber)));
        }

        isDone = true;
    }
}
