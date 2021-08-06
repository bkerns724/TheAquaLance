package theAquaLance.cards;

import com.badlogic.gdx.graphics.g3d.particles.influencers.DynamicsModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theAquaLance.actions.StackPowerAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class TacticalAssault extends AbstractEasyCard {
    public final static String ID = makeID("TacticalAssault");
    private final static int DAMAGE = 7;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public TacticalAssault() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (m != null) {
            if (m.hasPower(GainStrengthPower.POWER_ID)) {
                applyToEnemy(m, new StrengthPower(m, -magicNumber));
                if (!m.hasPower(ArtifactPower.POWER_ID))
                    applyToEnemy(m, new GainStrengthPower(m, magicNumber));
            }
            for (AbstractPower pow : m.powers) {
                if (pow.ID.equals(StrengthPower.POWER_ID) && m.hasPower(GainStrengthPower.POWER_ID))
                    continue;
                if (pow.type == AbstractPower.PowerType.DEBUFF)
                    atb(new StackPowerAction(pow, magicNumber));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}