package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theExile.damagemods.ScourgeType;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class SickBurn extends AbstractExileCard {
    public final static String ID = makeID(SickBurn.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public SickBurn() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        magicOneIsDebuff = true;
        addModifier(elenum.FIRE);
        DamageModifierManager.addModifier(this, new ScourgeType());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        int amount = getJinxAmountCard(m);
        if (amount > 0)
            applyToEnemy(m, new PoisonPower(m, p, magicNumber*amount));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}