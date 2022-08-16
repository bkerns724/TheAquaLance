package theExile.actions;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.ExileMod;
import theExile.damagemods.ForceDamage;
import theExile.relics.ManaPurifier;

import static theExile.util.Wiz.*;

public class GolemPunchAction extends AbstractGameAction {
    private final int damage;

    public GolemPunchAction(int damage) {
        this.damage = damage;
    }

    @Override
    public void update() {
        if (EnergyPanel.totalCount > 0) {
            adp().loseEnergy(1);
            AbstractMonster mon = AbstractDungeon.getMonsters().getRandomMonster(null, true,
                    AbstractDungeon.cardRandomRng);
            DamageInfo info = new DamageInfo(adp(), damage, DamageInfo.DamageType.THORNS);
            DamageModContainer container = new DamageModContainer(this, new ForceDamage());
            if (!adp().hasRelic(ManaPurifier.ID))
                DamageModifierManager.bindDamageMods(info, container);
            AttackEffect effect = ExileMod.Enums.FORCE_M;
            if (adp().hasRelic(ManaPurifier.ID))
                effect = AttackEffect.BLUNT_HEAVY;
            atb(new DamageAction(mon, info, effect));
        }

        isDone = true;
    }
}
