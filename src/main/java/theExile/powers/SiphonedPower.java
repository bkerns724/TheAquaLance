package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.ExileMod;
import theExile.damagemods.EldritchDamage;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class SiphonedPower extends AbstractExilePower {
    public static String POWER_ID = makeID(SiphonedPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SiphonedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void atStartOfTurn() {
        AbstractGameAction.AttackEffect effect = ExileMod.Enums.ELDRITCH;

        DamageInfo info = new DamageInfo(adp(), amount, DamageInfo.DamageType.THORNS);
        DamageModifierManager.bindDamageMods(info, new DamageModContainer(this, new EldritchDamage()));
        atb(new DamageAction(owner, info, effect));
    }
}