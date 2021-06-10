package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SpinKick extends AbstractEasyCard {
    public final static String ID = makeID("SpinKick");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 2;

    public SpinKick() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}