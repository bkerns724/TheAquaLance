package theArcanist.cards;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.WrathPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Madness extends AbstractArcanistCard {
    public final static String ID = makeID(Madness.class.getSimpleName());
    private final static int COST = 1;

    public Madness() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WrathPower(m));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("STANCE_ENTER_WRATH"));
    }

    public void upp() {
        selfRetain = true;
    }
}