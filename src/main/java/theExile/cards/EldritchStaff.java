package theExile.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.damagemods.EldritchDamage;
import theExile.powers.EldritchStaffPower;

import java.util.ArrayList;
import java.util.List;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class EldritchStaff extends AbstractExileCard {
    public final static String ID = makeID(EldritchStaff.class.getSimpleName());
    private final static int COST = 2;
    private final static int UPGRADED_COST = 1;
    private final static CardStrings eldritchStrings = CardCrawlGame.languagePack.getCardStrings(EldritchDamage.ID);

    public EldritchStaff() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() { }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (!adp().hasPower(EldritchStaffPower.POWER_ID))
            applyToSelf(new EldritchStaffPower(p));
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