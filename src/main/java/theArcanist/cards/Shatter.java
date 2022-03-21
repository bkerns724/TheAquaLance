package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.powers.CorrodedPower;
import theArcanist.powers.NauseousPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Shatter extends AbstractArcanistCard {
    public final static String ID = makeID("Shatter");
    private final static int COST = 1;
    public Shatter() {
        super(ID, COST, CardType.SKILL, ArcanistMod.Enums.UNIQUE, CardTarget.ENEMY);
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int x = getJinxAmount(m);
        if (x <= 0)
            return;
        applyToEnemy(m, new CorrodedPower(m, x));
        applyToEnemy(m, new NauseousPower(m, x));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void upp() {
    }
}