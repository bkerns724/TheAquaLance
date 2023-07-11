package theExile.cards;

import basemod.helpers.TooltipInfo;
import theExile.damagemods.ForceDamage;
import theExile.powers.EmpoweredStaffPower;

import java.util.ArrayList;
import java.util.List;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class
EmpoweredStaff extends AbstractExileCard {
    public final static String ID = makeID(EmpoweredStaff.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public EmpoweredStaff() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new EmpoweredStaffPower(magicNumber));
    }
    
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> list = new ArrayList<>();
        TooltipInfo info = new TooltipInfo(ForceDamage.cardStrings.DESCRIPTION,
                ForceDamage.cardStrings.EXTENDED_DESCRIPTION[0]);
        list.add(info);
        return list;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}