package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.PopAllAction;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Detonate extends AbstractEasyCard {
    public final static String ID = makeID("Detonate");
    private final static int SECOND_DAMAGE = 9;
    private final static int UPGRADE_SECOND = 3;
    private final static int COST = 2;

    public Detonate() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        EmbedPower pow = (EmbedPower) m.getPower(EmbedPower.POWER_ID);
        if (pow != null) {
            for (int i = 0; i < getShardCount(m); i++)
                allDmgTwo(AquaLanceMod.Enums.WATER);
            atb(new PopAllAction(m));
        }
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
    }
}