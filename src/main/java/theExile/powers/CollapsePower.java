package theExile.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.cards.Collapse;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class CollapsePower extends AbstractExilePower implements AtPlayerStartOfTurnPower {
    public static String POWER_ID = makeID(CollapsePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int offset = 0;
    private final Collapse card;

    public CollapsePower(AbstractCreature owner, Collapse card) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, -1);
        ID = POWER_ID + offset++;
        this.name = NAME;
        this.card = card;
    }

    @Override
    public void atPlayerStartOfTurn() {
        card.trigger((AbstractMonster) owner);
        atb(new RemoveSpecificPowerAction(owner, owner, this));
    }
}