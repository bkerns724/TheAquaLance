package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class CatalystShard extends AbstractEmbedCard {
    public final static String ID = makeID("CatalystShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int SECOND_DAMAGE = 5;
    private final static int UPGRADE_SECOND = 2;
    private final static int COST = 2;

    public CatalystShard() {
        super(ID, COST, CardRarity.RARE);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public void onReceivePower(AbstractCreature host, AbstractPower power,
                               AbstractCreature target, AbstractCreature source) {
        if (power.type == AbstractPower.PowerType.DEBUFF && !target.hasPower(ArtifactPower.POWER_ID) && target != source &&
                !power.ID.equals(GainStrengthPower.POWER_ID)) {
            if (power instanceof EmbedPower)
                if (((EmbedPower) power).cards.contains(this))
                    return;
            dmgTwoTop(host, AquaLanceMod.Enums.WATER);
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_SECOND);
    }

    public String getDesc() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + secondDamage + cardStrings.EXTENDED_DESCRIPTION[1];
    }
}