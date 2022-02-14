package theArcanist.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import theArcanist.ArcanistMod;
import theArcanist.powers.CrushedPower;
import theArcanist.powers.JinxPower;

import java.util.ArrayList;
import java.util.List;

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
    public List<TooltipInfo> getCustomTooltipsTop() {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        TooltipInfo info = new TooltipInfo(BaseMod.getKeywordTitle(ArcanistMod.SCOURGE_NAME),
                BaseMod.getKeywordDescription(ArcanistMod.SCOURGE_NAME));
        list.add(info);
        return list;
    }

    @Override
    public void onManualDiscard() {
        forAllMonstersLiving(monster -> {
            if (monster.hasPower(JinxPower.POWER_ID)) {
                int amount = monster.getPower(JinxPower.POWER_ID).amount;
                applyToEnemy(monster, new CrushedPower(monster, amount*magicNumber));
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}