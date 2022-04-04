package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.damageMods.SoulFireDamage;

import static theArcanist.ArcanistMod.makeID;

public class Eruption extends AbstractArcanistCard {
    public final static String ID = makeID(Eruption.class.getSimpleName());
    private final static int DAMAGE = 32;
    private final static int UPGRADE_DAMAGE = 8;
    private final static int COST = 3;

    public Eruption() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
        exhaust = true;
        DamageModifierManager.addModifier(this, new SoulFireDamage());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg(ArcanistMod.Enums.SOUL_FIRE);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}