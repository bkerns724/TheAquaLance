package theAquaLance.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theAquaLance.TheAquaLance;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class UnmeltingIce extends AbstractEasyRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("UnmeltingIce");
    private static final int EMBED_DAMAGE = 3;

    public UnmeltingIce() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, TheAquaLance.Enums.TURQUOISE_COLOR);
    }

    public boolean onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (!target.hasPower(ArtifactPower.POWER_ID) && power instanceof EmbedPower)
            atb(new DamageAction(target, new DamageInfo(source, EMBED_DAMAGE, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        return true;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + EMBED_DAMAGE + DESCRIPTIONS[1];
    }
}