package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WaterJet extends AbstractEasyCard {
    public final static String ID = makeID("WaterJet");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;

    public WaterJet() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AquaLanceMod.Enums.WATER);
        atb(new DrawCardAction(magicNumber));
        atb(new DiscardAction(adp(), adp(), magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}