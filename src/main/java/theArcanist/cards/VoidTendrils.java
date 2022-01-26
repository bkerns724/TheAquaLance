package theArcanist.cards;

import IconsAddon.util.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.DarkDamage;

import static theArcanist.ArcanistMod.DARK_COIL_EFFECT_FILE;
import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class VoidTendrils extends AbstractEasyCard {
    public final static String ID = makeID("VoidTendrils");
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public VoidTendrils() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        DamageModifierManager.addModifier(this, new DarkDamage());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.DARK_COIL);
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}