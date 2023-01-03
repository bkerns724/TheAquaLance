package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class ToxicSpellsPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ToxicSpellsPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ToxicSpellsPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL)
            Wiz.applyToEnemy(target, new CorrodedPower(target, amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}