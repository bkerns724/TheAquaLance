package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.patches.AbstractCardPatch;
import theAquaLance.powers.StreamlinedPower;

import java.util.Iterator;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SnowSigil extends AbstractEasyCard {
    public final static String ID = makeID("SnowSigil");
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public SnowSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        AbstractMonster strongestMonster = null;
        Iterator iter = AbstractDungeon.getMonsters().monsters.iterator();
        while(iter.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iter.next();
            if (!mo.isDeadOrEscaped()) {
                if (strongestMonster == null) {
                    strongestMonster = mo;
                } else if (mo.currentHealth > strongestMonster.currentHealth) {
                    strongestMonster = mo;
                }
            }
        }

        applyToEnemy(strongestMonster, new WeakPower(strongestMonster, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}