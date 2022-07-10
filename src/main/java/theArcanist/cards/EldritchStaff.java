package theArcanist.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.damagemods.EldritchDamage;
import theArcanist.powers.EldritchStaffPower;

import java.util.ArrayList;
import java.util.List;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class EldritchStaff extends AbstractArcanistCard {
    public final static String ID = makeID(EldritchStaff.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADED_COST = 0;
    private final static int COST = 1;
    private final static CardStrings eldritchStrings = CardCrawlGame.languagePack.getCardStrings(EldritchDamage.ID);

    public EldritchStaff() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EldritchStaffPower(p, magicNumber));
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        ArrayList<TooltipInfo> output = new ArrayList<>();
        output.add(new TooltipInfo(eldritchStrings.DESCRIPTION, eldritchStrings.EXTENDED_DESCRIPTION[0]));
        return output;
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}