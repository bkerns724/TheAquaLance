package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.DrowningPower;
import theAquaLance.powers.HobbledPower;
import theAquaLance.powers.VitalityPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class MarshSigil extends AbstractEasyCard {
    public final static String ID = makeID("MarshSigil");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public MarshSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving((m) -> {
            if (m.hasPower(WeakPower.POWER_ID))
                m.getPower(WeakPower.POWER_ID).amount += magicNumber;
            if (m.hasPower(VulnerablePower.POWER_ID))
                m.getPower(VulnerablePower.POWER_ID).amount += magicNumber;
            if (m.hasPower(HobbledPower.POWER_ID))
                m.getPower(HobbledPower.POWER_ID).amount += magicNumber;
            if (m.hasPower(DrowningPower.POWER_ID))
                m.getPower(DrowningPower.POWER_ID).amount += magicNumber;
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}