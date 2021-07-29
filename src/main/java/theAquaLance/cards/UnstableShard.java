package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class UnstableShard extends AbstractEmbedCard {
    public final static String ID = makeID("UnstableShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int SECOND_DAMAGE = 5;
    private final static int UPGRADE_SECOND = 2;
    private final static int COST = 1;

    public UnstableShard() {
        super(ID, COST, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public void onDiscardSigil(AbstractCreature host) {
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