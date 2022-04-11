package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.damagemods.ScourgeType;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.getJinxAmount;

public class SuddenChill extends AbstractArcanistCard {
    public final static String ID = makeID(SuddenChill.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int COST = 1;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public SuddenChill() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        magicOneIsDebuff = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
        addModifier(elenum.ICE);
        hasScourge = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.ICE);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        // NOT getJinxAmountCard
        baseDamage += magicNumber*getJinxAmount(mo);
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public void initializeDescription() {
        if (scourgeIncrease && upgraded)
            baseMagicNumber = (MAGIC + UPGRADE_MAGIC)*2;
        else if (scourgeIncrease)
            baseMagicNumber = MAGIC*2;
        else
            baseMagicNumber = MAGIC;
        magicNumber = baseMagicNumber;
        super.initializeDescription();
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}