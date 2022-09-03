package theExile.cards;

import basemod.helpers.TooltipInfo;
import theExile.actions.ConstantPracticeAction;

import java.util.ArrayList;
import java.util.List;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class ConstantPractice extends AbstractExileCard {
    public final static String ID = makeID(ConstantPractice.class.getSimpleName());
    private final static int COST = 3;
    private final static int UPGRADED_COST = 2;

    public ConstantPractice() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
    }

    public void nonTargetUse() {
        atb(new ConstantPracticeAction());
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        TooltipInfo tip = new TooltipInfo("Disclaimer", "Can not Fetch another Constant Practice");
        return list;
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}