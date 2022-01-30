package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theArcanist.ArcanistMod.makeID;

public class WhirlpoolSigil extends AbstractSigilCard {
    public final static String ID = makeID("WhirlpoolSigil");
    private final static int MAGIC = 2;

    public WhirlpoolSigil() {
        super(ID, CardRarity.COMMON);
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
        return false;
    }

    @Override
    public void onManualDiscard() {
    }

    public void upp() {

    }
}