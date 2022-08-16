package theExile.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.ExileMod;
import theExile.damagemods.IceDamage;
import theExile.relics.ManaPurifier;

import static theExile.util.Wiz.*;

public class GolemFrostAction extends AbstractGameAction {
    private final int damage;
    public GolemFrostAction(int damage) {
        this.damage = damage;
    }

    @Override
    public void update() {
        if (EnergyPanel.totalCount > 0) {
            adp().loseEnergy(1);
            DamageInfo info = new DamageInfo(adp(), damage, DamageInfo.DamageType.THORNS);
            DamageModContainer container = new DamageModContainer(this, new IceDamage());
            if (!adp().hasRelic(ManaPurifier.ID))
                DamageModifierManager.bindDamageMods(info, container);
            AttackEffect effect = ExileMod.Enums.ICE_M;
            if (adp().hasRelic(ManaPurifier.ID))
                effect = AttackEffect.SLASH_HEAVY;
            for (AbstractMonster mon : getEnemies())
                atb(new DamageAction(mon, info, effect));
        }

        isDone = true;
    }
}
