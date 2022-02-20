package theArcanist.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theArcanist.ArcanistMod;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theArcanist.actions.MyAddTempHPAction;

import static theArcanist.util.Wiz.*;

public class DarkerEmbracePower extends AbstractArcanistPower {
    // intellij stuff DarkerEmbrace, debuff, false
    public static String POWER_ID = ArcanistMod.makeID("DarkerEmbrace");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DarkerEmbracePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        att(new MyAddTempHPAction(adp(), adp(), amount));
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}