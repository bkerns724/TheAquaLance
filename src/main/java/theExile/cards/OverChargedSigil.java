package theExile.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class OverChargedSigil extends AbstractExileCard {
    public final static String ID = makeID(OverChargedSigil.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 4;
    private final static int ENERGY_AMOUNT = 2;
    private final static int COST = -2;

    public OverChargedSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new LoseHPAction(adp(), adp(), secondMagic));
        cardDraw(magicNumber);
        atb(new GainEnergyAction(ENERGY_AMOUNT));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}