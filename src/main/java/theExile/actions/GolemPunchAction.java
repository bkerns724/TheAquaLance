package theExile.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.ExileMod;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.thornDmgTop;

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
            thornDmgTop(mon, damage, ExileMod.Enums.FORCE_M);
        }

        isDone = true;
    }
}
