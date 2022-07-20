package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.damagemods.ScourgeType;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class HexedSigil extends AbstractExileCard {
    public final static String ID = makeID(HexedSigil.class.getSimpleName());
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = -2;

    public HexedSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> atb(new LoseHPAction(mon, adp(), magicNumber*getJinxAmountCard(mon))));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}