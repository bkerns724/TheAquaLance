package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theArcanist.actions.ApplyBaneAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;
import static theArcanist.util.Wiz.atb;

public class Bane extends AbstractArcanistCard {
    public final static String ID = makeID(Bane.class.getSimpleName());
    private final static int MAGIC = 0;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -1;

    public Bane() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        exhaust = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyBaneAction(m, freeToPlayOnce, energyOnUse, upgraded));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        magicNumber = baseMagicNumber;
        if (adp().hasRelic(ChemicalX.ID))
            magicNumber += 2;
        magicNumber += EnergyPanel.totalCount;
        isMagicNumberModified = true;
        initializeDescription();
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}