package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.damagemods.ScourgeType;
import theExile.powers.JinxThornsPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ScourgeBubble extends AbstractExileCard {
    public final static String ID = makeID(ScourgeBubble.class.getSimpleName());
    private final static int BLOCK = 12;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 2;

    public ScourgeBubble() {
        super(ID, COST, CardType.SKILL, ExileMod.Enums.UNIQUE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        DamageModifierManager.addModifier(this, new ScourgeType());
        hasScourge = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new JinxThornsPower(p, magicNumber));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        magicNumber = baseMagicNumber;
        super.calculateCardDamage(mo);
        if (scourgeIncrease)
            magicNumber *= 2;
    }

    @Override
    public void applyPowers() {
        magicNumber = baseMagicNumber;
        super.applyPowers();
        if (scourgeIncrease)
            magicNumber *= 2;
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}