package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SavageThrust extends AbstractEasyCard {
    public final static String ID = makeID("SavageThrust");
    private final static int DAMAGE = 20;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public SavageThrust() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        atb(new MakeTempCardInDrawPileAction(new VoidCard(), magicNumber, true, true));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}