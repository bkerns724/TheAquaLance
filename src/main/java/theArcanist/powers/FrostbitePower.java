package theArcanist.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import static theArcanist.util.Wiz.*;

public class FrostbitePower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID("Frostbite");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrostbitePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner == owner) {
            flash();
            att(new DamageAction(owner,
                    new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
            if (amount > 1)
                reducePower(1);
            else
                att(new RemoveSpecificPowerAction(owner, owner, this));
            updateDescription();
            AbstractDungeon.onModifyPower();
        }
    }

    @Override
    public void upDescription() {
        description = DESCRIPTIONS[0];
    }
}