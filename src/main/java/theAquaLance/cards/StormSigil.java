package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import theAquaLance.actions.TriggerEnemyMarkAction;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class StormSigil extends AbstractEasyCard {
    public final static String ID = makeID("StormSigil");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public StormSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        forAllMonstersLiving(m -> {
            int count = getShardCount(m);
            if (count > 0) {
                applyToEnemy(m, new MarkPower(m, magicNumber*count));
                atb(new TriggerEnemyMarkAction(m));
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}