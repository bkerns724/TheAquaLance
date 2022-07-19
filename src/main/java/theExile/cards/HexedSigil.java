package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.HexingPower;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class HexedSigil extends AbstractExileCard {
    public final static String ID = makeID(HexedSigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = -2;

    public HexedSigil() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HexingPower(1));
        if (upgraded)
            forAllMonstersLiving(mon -> applyToEnemy(mon, new JinxPower(mon, magicNumber)));
    }

    public void upp() {
    }
}