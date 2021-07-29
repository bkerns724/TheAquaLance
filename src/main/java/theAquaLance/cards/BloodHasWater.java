package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BloodHasWater extends AbstractEasyCard {
    public final static String ID = makeID("BloodHasWater");
    private final static int SECOND_DAMAGE = 8;
    private final static int UPGRADE_SECOND = 4;
    private final static int MAGIC = 1;

    public BloodHasWater() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTwo(m, AquaLanceMod.Enums.BLOOD);
        atb(new DrawCardAction(magicNumber));
        atb(new DiscardAction(adp(), adp(), magicNumber, false));
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
    }
}