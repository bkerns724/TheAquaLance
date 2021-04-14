package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.EnergizedTurquoisePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Maneuver extends AbstractEasyCard {
    public final static String ID = makeID("Maneuver");
    private final static int MAGIC = 2;
    private final static int SECOND_MAGIC = 1;

    public Maneuver() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawCardNextTurnPower(p, magicNumber));
        applyToSelf(new EnergizedTurquoisePower(p, secondMagic));
    }

    public void upp() {
        AbstractCardPatch.AbstractCardField.replenish.set(this, true);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}