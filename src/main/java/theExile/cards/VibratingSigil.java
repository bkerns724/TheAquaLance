package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.VibratingPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class VibratingSigil extends AbstractExileCard {
    public final static String ID = makeID(VibratingSigil.class.getSimpleName());
    private final static int MAGIC = 50;
    private final static int UPGRADE_MAGIC = 25;
    private final static int COST = -2;

    public VibratingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VibratingPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}