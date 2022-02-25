package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.powers.FrostbitePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class SuddenChill extends AbstractArcanistCard {
    public final static String ID = makeID("SuddenChill");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public SuddenChill() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.ICE);
        if (getJinxAmount(m) > 0)
            applyToEnemy(m, new FrostbitePower(m, getJinxAmount(m)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}