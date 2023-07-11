package theExile.cards;

import basemod.helpers.TooltipInfo;
import theExile.damagemods.DeathLightningDamage;
import theExile.powers.BatteryPower;

import java.util.ArrayList;
import java.util.List;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class Battery extends AbstractExileCard {
    public final static String ID = makeID(Battery.class.getSimpleName());
    private final static int COST = 1;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public Battery() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        applyToSelf(new BatteryPower(magicNumber));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> list = new ArrayList<>();
        TooltipInfo info = new TooltipInfo(DeathLightningDamage.cardStrings.DESCRIPTION,
                DeathLightningDamage.cardStrings.EXTENDED_DESCRIPTION[0]);
        list.add(info);
        return list;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}