package theExile.cards;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Dispel extends AbstractExileCard {
    public final static String ID = makeID(Dispel.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public Dispel() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new RemoveDebuffsAction(adp()));
        applyToSelf(new ArtifactPower(adp(), magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}