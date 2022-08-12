package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.HastePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Haste extends AbstractExileCard {
    public final static String ID = makeID(Haste.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public Haste() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HastePower(magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}