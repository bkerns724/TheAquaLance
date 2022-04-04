package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;


import static theArcanist.util.Wiz.*;

public class SoulFlameBarrierPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(SoulFlameBarrierPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SoulFlameBarrierPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && info.owner != this.owner) {
            flash();
            thornDmgTop(info.owner, amount, ArcanistMod.Enums.SOUL_FIRE);
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        removePower(this);
    }
}