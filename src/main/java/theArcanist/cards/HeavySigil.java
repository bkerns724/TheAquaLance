package theArcanist.cards;

import theArcanist.powers.CrushedPower;
import theArcanist.powers.CursePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class HeavySigil extends AbstractSigilCard {
    public final static String ID = makeID("HeavySigil");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public HeavySigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving(monster -> {
            if (monster.hasPower(CursePower.POWER_ID)) {
                int amount = monster.getPower(CursePower.POWER_ID).amount;
                applyToEnemy(monster, new CrushedPower(monster, amount*magicNumber));
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}