package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class PoisonShard extends AbstractEmbedCard {
    public final static String ID = makeID("PoisonShard");
    private final static int DAMAGE = 3;
    private final static int SECOND_DAMAGE = 2;
    private final static int UPGRADE_SECOND = 1;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int COST = 1;

    public PoisonShard() {
        super(ID, COST, CardRarity.COMMON);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public void atStartOfTurn(AbstractCreature host) {
        dmgTwo(host, AbstractGameAction.AttackEffect.POISON);
    }

    public String getDesc() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + secondDamage + cardStrings.EXTENDED_DESCRIPTION[1];
    }

    public void upp() {
        upgradeSecondDamage(UPGRADE_SECOND);
        upgradeDamage(UPGRADE_DAMAGE);
    }
}