package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.FinisherAction;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;
import theAquaLance.powers.StormSigilPower;

import java.util.Iterator;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class StormSigil extends AbstractEasyCard {
    public final static String ID = makeID("StormSigil");
    private final static int MAGIC = 3;
    private final static int SECOND_MAGIC = 15;
    private final static int UPGRADE_SECOND_MAGIC = 5;

    public StormSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
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
        super.triggerOnManualDiscard();
    }

    public void OnManualDiscard() {
        applyToSelf(new StormSigilPower(adp(), secondMagic, magicNumber));
    }

    private static boolean isSigil(AbstractCard c) { return AbstractCardField.sigil.get(c); }

    public void upp() {
        upgradeSecondMagic(UPGRADE_SECOND_MAGIC);
    }
}