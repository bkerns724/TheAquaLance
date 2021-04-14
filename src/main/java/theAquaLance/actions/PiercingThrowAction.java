package theAquaLance.actions;//

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static theAquaLance.util.Wiz.adp;
import static theAquaLance.util.Wiz.att;

public class PiercingThrowAction extends AbstractGameAction {
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private final static int DRAW_AMOUNT = 1;
    private final static int ENERGY_AMOUNT = 1;

    public PiercingThrowAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        actionType = ActionType.DAMAGE;
        duration = DURATION;
    }

    public void update() {
        if (duration == DURATION && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.SLASH_HEAVY));
            target.damage(info);
            if ((((AbstractMonster)target).isDying || target.currentHealth <= 0) && !target.halfDead) {
                att(new AquaDrawCardAction(DRAW_AMOUNT));
                att(new GainEnergyAction(ENERGY_AMOUNT));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        tickDuration();
    }
}
