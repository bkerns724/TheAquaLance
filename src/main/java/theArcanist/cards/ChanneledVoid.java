package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.DarkDamage;
import theArcanist.patches.ResonantPowerPatch.AbstractCardField;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChanneledVoid extends AbstractArcanistCard {
    public final static String ID = makeID("ChanneledVoid");
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public ChanneledVoid() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        DamageModifierManager.addModifier(this, new DarkDamage());
        AbstractCardField.resonance.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.DARK_COIL);
        applyToSelf(new ResonatingPower(p, baseDamage, false, true, false, false, 0));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}