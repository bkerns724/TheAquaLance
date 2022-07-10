package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.actions.ApplyShadowcloakAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;

public class Fade extends AbstractExileCard {
    public final static String ID = makeID(Fade.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -1;

    public Fade() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyShadowcloakAction(freeToPlayOnce, energyOnUse, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}