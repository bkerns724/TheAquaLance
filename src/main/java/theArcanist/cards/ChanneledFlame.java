package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.SoulFireDamage;
import theArcanist.patches.ResonantPowerPatch.AbstractCardField;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChanneledFlame extends AbstractArcanistCard {
    public final static String ID = makeID("ChanneledFlame");
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 1;

    public ChanneledFlame() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        DamageModifierManager.addModifier(this, new SoulFireDamage());
        AbstractCardField.resonance.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.SOUL_FIRE);
        applyToSelf(new ResonatingPower(p, baseDamage, false, false, false, true));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}