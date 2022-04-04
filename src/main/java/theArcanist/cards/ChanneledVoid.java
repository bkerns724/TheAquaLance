package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.damageMods.DarkDamage;

import static theArcanist.ArcanistMod.makeID;

public class ChanneledVoid extends AbstractArcanistCard {
    public final static String ID = makeID(ChanneledVoid.class.getSimpleName());
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public ChanneledVoid() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        DamageModifierManager.addModifier(this, new DarkDamage());
        resonant = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.DARK_COIL);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}