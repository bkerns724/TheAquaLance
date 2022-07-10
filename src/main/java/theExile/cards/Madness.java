package theExile.cards;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.ReturnToHandAction;
import theExile.powers.WrathPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.atb;

public class Madness extends AbstractExileCard {
    public final static String ID = makeID(Madness.class.getSimpleName());
    private final static int COST = 0;

    public Madness() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        selfRetain = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WrathPower(m));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("STANCE_ENTER_WRATH"));
    }

    @Override
    public void triggerOnDeath() {
        if (upgraded)
            atb(new ReturnToHandAction(this));
    }

    @Override
    public void upp() { }
}