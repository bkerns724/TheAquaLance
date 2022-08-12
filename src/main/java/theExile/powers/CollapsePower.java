package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;
import theExile.cards.AbstractExileCard;
import theExile.damagemods.ForceDamage;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class CollapsePower extends AbstractExilePower implements AtPlayerStartOfTurnPower {
    public static String POWER_ID = makeID(CollapsePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CollapsePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atPlayerStartOfTurn() {
        AbstractGameAction.AttackEffect effect = ExileMod.Enums.FORCE;
        if (amount > AbstractExileCard.DAMAGE_THRESHOLD_L)
            effect = ExileMod.Enums.FORCE_L;
        else if (amount > AbstractExileCard.DAMAGE_THRESHOLD_M)
            effect = ExileMod.Enums.FORCE_M;
        DamageInfo info = new DamageInfo(adp(), amount, DamageInfo.DamageType.THORNS);
        DamageModifierManager.bindDamageMods(info, new DamageModContainer(this, new ForceDamage()));
        atb(new DamageAction(owner, info, effect));
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}