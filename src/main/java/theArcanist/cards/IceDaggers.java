package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.IceDamage;

import static theArcanist.ArcanistMod.makeID;

public class IceDaggers extends AbstractArcanistCard {
    public final static String ID = makeID("IceDaggers");
    private final static int DAMAGE = 2;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 3;
    private final static int COST = 1;

    public IceDaggers() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        DamageModifierManager.addModifier(this, new IceDamage());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++)
            dmg(m, ArcanistMod.Enums.ICE);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}