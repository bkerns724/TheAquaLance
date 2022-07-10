package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.SpiritPressurePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class MenacingPresence extends AbstractExileCard {
    public final static String ID = makeID(MenacingPresence.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public MenacingPresence() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SpiritPressurePower(p, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}