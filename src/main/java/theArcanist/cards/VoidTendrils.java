package theArcanist.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;

public class VoidTendrils extends AbstractArcanistCard {
    public final static String ID = makeID(VoidTendrils.class.getSimpleName());
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public VoidTendrils() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.DARK);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.DARK_COIL);
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}