package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToEnemy;
import static theArcanist.util.Wiz.getDebuffCount;

public class CursedLance extends AbstractArcanistCard {
    public final static String ID = makeID(CursedLance.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public CursedLance() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        int count = getDebuffCount(m);
        if (count > 0)
            applyToEnemy(m, new JinxPower(m, magicNumber*count));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}