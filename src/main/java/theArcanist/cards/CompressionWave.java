package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.damageMods.ForceDamage;

import static theArcanist.ArcanistMod.makeID;

public class CompressionWave extends AbstractArcanistCard {
    public final static String ID = makeID(CompressionWave.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 2;

    public CompressionWave() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
        DamageModifierManager.addModifier(this, new ForceDamage());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg(ArcanistMod.Enums.FIST);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}