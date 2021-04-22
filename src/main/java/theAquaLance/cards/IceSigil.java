package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class IceSigil extends AbstractEasyCard {
    public final static String ID = makeID("IceSigil");
    private final static int MAGIC = 7;
    private final static int UPGRADE_MAGIC = 2;

    public IceSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnManualDiscard() {
        atb(new GainBlockAction(adp(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}