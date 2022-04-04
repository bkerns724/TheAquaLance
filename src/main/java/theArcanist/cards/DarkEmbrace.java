package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.damageMods.DarkDamage;
import theArcanist.powers.DarkerEmbracePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class DarkEmbrace extends AbstractArcanistCard {
    public final static String ID = makeID(DarkEmbrace.class.getSimpleName());
    private final static int DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public DarkEmbrace() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        DamageModifierManager.addModifier(this, new DarkDamage());
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.DARK_COIL);
        applyToEnemy(m, new DarkerEmbracePower(m, magicNumber));
    }

    public void upp() {
        exhaust = false;
    }
}