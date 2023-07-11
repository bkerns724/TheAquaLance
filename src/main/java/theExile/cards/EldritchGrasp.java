package theExile.cards;

import basemod.helpers.TooltipInfo;
import theExile.damagemods.EldritchDamage;
import theExile.powers.EldritchGraspPower;

import java.util.ArrayList;
import java.util.List;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class EldritchGrasp extends AbstractExileCard {
    public final static String ID = makeID(EldritchGrasp.class.getSimpleName());
    private final static int COST = 1;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public EldritchGrasp() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new EldritchGraspPower(magicNumber));
    }
    
    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> list = new ArrayList<>();
        TooltipInfo info = new TooltipInfo(EldritchDamage.cardStrings.DESCRIPTION,
                EldritchDamage.cardStrings.EXTENDED_DESCRIPTION[0]);
        list.add(info);
        return list;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}