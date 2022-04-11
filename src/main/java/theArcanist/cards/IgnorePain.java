package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.IgnorePainAction;
import theArcanist.powers.IgnorePainPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToSelf;
import static theArcanist.util.Wiz.atb;

public class IgnorePain extends AbstractArcanistCard {
    public final static String ID = makeID(IgnorePain.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public IgnorePain() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new IgnorePainPower(p, magicNumber));
        if (upgraded)
            atb(new IgnorePainAction());
    }

    public void upp() {
    }
}