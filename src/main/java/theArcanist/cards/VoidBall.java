package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.DarkDamage;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class VoidBall extends AbstractArcanistCard {
    public final static String ID = makeID("VoidBall");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public VoidBall() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        DamageModifierManager.addModifier(this, new DarkDamage());
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(ArcanistMod.Enums.DARK_COIL);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}