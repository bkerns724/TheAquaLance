package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.DrainingStrikesPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class DrainingStrikes extends AbstractExileCard {
    public final static String ID = makeID(DrainingStrikes.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public DrainingStrikes() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrainingStrikesPower(magicNumber));
    }

    public void upp() { isInnate = true; }
}