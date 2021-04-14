package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class HailSigil extends AbstractEasyCard {
    public final static String ID = makeID("HailSigil");
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public HailSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        AbstractMonster weakestMonster = null;
        Iterator iter = AbstractDungeon.getMonsters().monsters.iterator();
        while(iter.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iter.next();
            if (!mo.isDeadOrEscaped()) {
                if (weakestMonster == null) {
                    weakestMonster = mo;
                } else if (mo.currentHealth < weakestMonster.currentHealth) {
                    weakestMonster = mo;
                }
            }
        }

        applyToEnemy(weakestMonster, new VulnerablePower(weakestMonster, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}