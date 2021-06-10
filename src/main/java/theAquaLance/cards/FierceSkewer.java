package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class FierceSkewer extends AbstractEasyCard {
    public final static String ID = makeID("FierceSkewer");
    private final static int DAMAGE = 25;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = -1;

    public FierceSkewer() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return (getDebuffCount(m) >= magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}