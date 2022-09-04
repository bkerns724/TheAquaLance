package theExile.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class WetPower extends AbstractExilePower {
    public static String POWER_ID = makeID(WetPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public WetPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn() {
        FrostbitePower power = (FrostbitePower) owner.getPower(FrostbitePower.POWER_ID);
        if (power == null)
            return;
        int count = power.amount;
        DamageInfo info = new DamageInfo(adp(), count*amount, DamageInfo.DamageType.THORNS);
        flash();
        atb(new DamageAction(owner,info, AbstractGameAction.AttackEffect.NONE));
    }
}