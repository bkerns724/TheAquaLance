package theExile.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.damagemods.IceDamage;

import static theExile.util.Wiz.*;

public class FrigidBlastAction extends AbstractGameAction {
    private final AbstractMonster m;
    private final boolean freeToPlayOnce;
    private final int damage;
    private final DamageInfo.DamageType damageTypeForTurn;
    private final int energyOnUse;
    private final AttackEffect attackEffect;
    private final DamageModContainer container;

    public FrigidBlastAction(AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn,
                             boolean freeToPlayOnce, int energyOnUse, AttackEffect attackEffect) {
        this.m = m;
        this.damage = damage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = startDuration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.DAMAGE;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
        this.attackEffect = attackEffect;
        container = new DamageModContainer(this, new IceDamage());
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic(ChemicalX.ID)) {
            effect += 2;
            adp().getRelic(ChemicalX.ID).flash();
        }


        if (effect > 0) {
            DamageInfo info = new DamageInfo(adp(), damage, damageTypeForTurn);
            DamageModifierManager.bindDamageMods(info, container);
            for (int i = 0; i < effect; ++i)
                att(new DamageAction(m, info, attackEffect));

            applyToSelfTop(new DrawCardNextTurnPower(adp(), effect));
        }

        if (!freeToPlayOnce)
            adp().energy.use(EnergyPanel.totalCount);

        isDone = true;
    }
}
