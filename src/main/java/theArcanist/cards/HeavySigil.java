package theArcanist.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.powers.CrushedPower;

import java.util.ArrayList;
import java.util.List;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class HeavySigil extends AbstractArcanistCard {
    public final static String ID = makeID(HeavySigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public HeavySigil() {
        super(ID, -2, CardType.SKILL,CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
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
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(monster -> {
            int amount = getJinxAmount(monster);
            if (amount > 0)
                applyToEnemy(monster, new CrushedPower(monster, amount * magicNumber));
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}