package theExile.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.util.EnergyChangeSubscriber;

import static theExile.util.Wiz.adp;

@SpirePatch2(clz = EnergyPanel.class, method = "setEnergy")
@SpirePatch2(clz = EnergyPanel.class, method = "addEnergy")
@SpirePatch2(clz = EnergyPanel.class, method = "useEnergy")
public class EnergySubscriberPatch {
    @SpirePostfixPatch
    public static void Postfix() {
        if (adp() == null)
            return;
        for (AbstractPower pow : adp().powers)
            if (pow instanceof EnergyChangeSubscriber)
                ((EnergyChangeSubscriber) pow).onChangeEnergy();
        for (AbstractRelic relic : adp().relics)
            if (relic instanceof EnergyChangeSubscriber)
                ((EnergyChangeSubscriber) relic).onChangeEnergy();
    }
}
