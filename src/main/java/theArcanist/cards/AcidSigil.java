package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.AcidSigilAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;

public class AcidSigil extends AbstractArcanistCard {
    public final static String ID = makeID(AcidSigil.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = -2;

    public AcidSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    protected void applyAttributes() {
        sigil = true;
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new AcidSigilAction(this));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}