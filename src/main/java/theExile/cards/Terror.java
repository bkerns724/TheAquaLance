package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.damagemods.ScourgeType;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Terror extends AbstractExileCard {
    public final static String ID = makeID(Terror.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int DISCARD_AMOUNT = 1;
    private final static int SECOND_MAGIC = 1;
    private final static int UPGRADE_SECOND = 1;

    public Terror() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
        magicOneIsDebuff = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (getJinxAmountCard(m) > 0)
            applyToEnemy(m, new VulnerablePower(m, magicNumber*getJinxAmountCard(m), false));
        atb(new DrawCardAction(secondMagic));
        discard(DISCARD_AMOUNT);
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}