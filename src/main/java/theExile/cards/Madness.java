package theExile.cards;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.powers.WrathPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Madness extends AbstractExileCard {
    public final static String ID = makeID(Madness.class.getSimpleName());
    private final static int COST = 1;
    private final static int MAGIC = 1;

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
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    @Override
    public void upp() { }
}