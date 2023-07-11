package theExile.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theExile.cards.AbstractExileCard;
import theExile.cards.StaffStrike;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class EmpoweredStaffPower extends AbstractExilePower {
    public static final String POWER_ID = makeID(EmpoweredStaffPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EmpoweredStaffPower(int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card instanceof StaffStrike)
            return damage + amount;
        return damage;
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        for (AbstractCard c : adp().drawPile.group)
            if (c instanceof StaffStrike)
                ((AbstractExileCard)c).addModifier(AbstractExileCard.elenum.FORCE);
        for (AbstractCard c : adp().discardPile.group)
            if (c instanceof StaffStrike)
                ((AbstractExileCard)c).addModifier(AbstractExileCard.elenum.FORCE);
        for (AbstractCard c : adp().exhaustPile.group)
            if (c instanceof StaffStrike)
                ((AbstractExileCard)c).addModifier(AbstractExileCard.elenum.FORCE);
        for (AbstractCard c : adp().hand.group)
            if (c instanceof StaffStrike)
                ((AbstractExileCard)c).addModifier(AbstractExileCard.elenum.FORCE);
        for (AbstractCard c : adp().limbo.group)
            if (c instanceof StaffStrike)
                ((AbstractExileCard)c).addModifier(AbstractExileCard.elenum.FORCE);
    }
}