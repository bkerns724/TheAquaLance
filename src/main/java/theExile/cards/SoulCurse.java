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

    public SoulCurse() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        exhaust = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
        hasScourge = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int count = getJinxAmountCard(m);
        if (count > 0)
            applyToEnemy(m, new JinxPower(m, count));
    }

    public void upp() {
        exhaust = false;
    }
}