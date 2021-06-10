package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;
import com.megacrit.cardcrawl.powers.ToolsOfTheTradePower;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.SpeedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Speed extends AbstractEasyCard {
    public final static String ID = makeID("Speed");
    private final static int MAGIC = 1;

    public Speed() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SpeedPower(p, magicNumber));
    }

    public void upp() {
        isInnate = true;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}