package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theAquaLance.TheAquaLance;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class UnmeltingIce extends AbstractEasyRelic {
    public static final String ID = makeID("UnmeltingIce");
    private static final int POP_DAMAGE = 4;

    public UnmeltingIce() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheAquaLance.Enums.AQUALANCE_TURQUOISE_COLOR);
    }

    public void onTrigger(AbstractCreature target) {
        atb(new DamageAction(target, new DamageInfo(adp(), POP_DAMAGE, DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + POP_DAMAGE + DESCRIPTIONS[1];
    }
}