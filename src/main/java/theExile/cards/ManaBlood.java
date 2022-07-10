package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ManaBlood extends AbstractExileCard {
    public final static String ID = makeID(ManaBlood.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public ManaBlood() {
        super(ID, COST, CardType.POWER, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawPower(p, magicNumber));
        applyToSelf(new BerserkPower(p, magicNumber));
    }

    public void upp() {
        selfRetain = true;
    }
}