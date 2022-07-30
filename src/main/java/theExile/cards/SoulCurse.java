package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.damagemods.ScourgeType;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SoulCurse extends AbstractExileCard {
    public final static String ID = makeID(SoulCurse.class.getSimpleName());
    private final static int COST = 1;
    private final static int UPGRADED_COST = 0;
    private final static int MAGIC = 1;

    public SoulCurse() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        exhaust = true;
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int count = getJinxAmountCard(m);
        if (count > 0)
            applyToEnemy(m, new JinxPower(m, magicNumber*count));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}