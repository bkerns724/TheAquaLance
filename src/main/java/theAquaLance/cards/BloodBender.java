package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.PopAllAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodBender extends AbstractEasyCard {
    public final static String ID = makeID("BloodBender");
    private final static int SECOND_DAMAGE = 18;
    private final static int UPGRADE_SECOND = 6;

    public BloodBender() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);
        for (int i = 0; i < getShardCount(m); i++)
            dmgTwo(m, AquaLanceMod.Enums.BLOOD);
        atb(new PopAllAction(m));
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
    }
}