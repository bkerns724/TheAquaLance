package theExile.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.cards.BrrZerk;

import static theExile.util.Wiz.*;

public class brrZerkAction extends AbstractGameAction {
    private final BrrZerk card;
    private final boolean free;
    private final int energyOnUse;
    private final DamageModContainer container;

    public brrZerkAction(AbstractMonster m, BrrZerk card, boolean freeToPlayOnce, int energyOnUse, DamageModContainer container) {
        this.card = card;
        target = m;
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DAMAGE;
        free = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        this.container = container;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic("Chemical X")) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            DamageInfo info = BindingHelper.makeInfo(container, adp(), card.damage, DamageInfo.DamageType.NORMAL);
            for(int i = 0; i < effect; ++i) {
                AttackEffect attackEffect = getAttackEffect(card.damage, card.damageModList, false);
                att(new AttackAction((AbstractMonster) target, info, attackEffect));
            }

            if (!free)
                adp().energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
