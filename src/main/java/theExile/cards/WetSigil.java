package theExile.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import theExile.ExileMod;
import theExile.powers.FrostbitePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class WetSigil extends AbstractExileCard {
    public final static String ID = makeID(WetSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public WetSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(mon -> {
            FrostbitePower power = (FrostbitePower) mon.getPower(FrostbitePower.POWER_ID);
            if (power != null && power.amount > 0) {
                DamageInfo info = new DamageInfo(adp(), power.amount*magicNumber, DamageInfo.DamageType.THORNS);
                atb(new DamageAction(mon, info, ExileMod.Enums.WATER, true));
            }
        });
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}