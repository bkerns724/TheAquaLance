package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theAquaLance.actions.TriggerEnemyMarkAction;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import java.util.ArrayList;
import java.util.Objects;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class InscribedSigil extends AbstractEasyCard {
    public final static String ID = makeID("InscribedSigil");
    private final static int MAGIC = 6;
    private final static int UPGRADE_MAGIC = 2;

    public InscribedSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void onManualDiscard() {
        ArrayList<AbstractMonster> enemies = getEnemies();
        enemies.removeIf(m -> m.halfDead);
        enemies.removeIf(Objects::isNull); // Might not even be possible, but making sure.
        for (AbstractMonster m : enemies) {
            applyToEnemy(m, new MarkPower(m, magicNumber));
            atb(new TriggerEnemyMarkAction(m));
        }
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}