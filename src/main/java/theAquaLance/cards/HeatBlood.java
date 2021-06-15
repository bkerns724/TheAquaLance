package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class HeatBlood extends AbstractEasyCard {
    public final static String ID = makeID("HeatBlood");
    private final static int SECOND_DAMAGE = 8;
    private final static int UPGRADE_SECOND = 4;

    public HeatBlood() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTwo(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new DrawCardAction(1));
        atb(new DiscardAction(adp(), adp(), 1, false));
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
    }
}