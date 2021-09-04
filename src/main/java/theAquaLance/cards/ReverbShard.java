package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ReverbShard extends AbstractEmbedCard {
    public final static String ID = makeID("ReverbShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int SECOND_DAMAGE = 4;
    private final static int UPGRADE_SECOND = 1;
    private final static int COST = 1;

    public ReverbShard() {
        super(ID, COST, CardRarity.BASIC);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public void onDiscard(AbstractCreature host) {
        dmgTwo(host, AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_SECOND);
    }

    public String getDesc() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + secondDamage + cardStrings.EXTENDED_DESCRIPTION[1];
    }
}