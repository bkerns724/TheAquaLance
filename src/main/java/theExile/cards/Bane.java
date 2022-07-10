package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.actions.ApplyBaneAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class Bane extends AbstractExileCard {
    public final static String ID = makeID(Bane.class.getSimpleName());
    private final static int MAGIC = 0;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -1;

    public Bane() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyBaneAction(m, freeToPlayOnce, energyOnUse, upgraded));
    }

    @Override
    public void applyPowers() {
        magicNumber = baseMagicNumber;
        super.applyPowers();
        if (adp().hasRelic(ChemicalX.ID))
            magicNumber += 2;
        if (debuffIncrease)
            magicNumber += EnergyPanel.totalCount*2;
        else
            magicNumber += EnergyPanel.totalCount;
        isMagicNumberModified = true;
        initializeDescription();
    }

    @Override
    protected String getCustomString() {
        if (debuffIncrease)
            return cardStrings.EXTENDED_DESCRIPTION[0];
        return "";
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}