package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.patches.AbstractCardPatch;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class FloodSigil extends AbstractSigilCard {
    public final static String ID = makeID("FloodSigil");
    private final static int SECOND_DAMGE = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public FloodSigil() {
        super(ID, CardRarity.UNCOMMON);
        baseSecondDamage = SECOND_DAMGE;
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onManualDiscard() {
        AbstractMonster weakestMonster = null;
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped() && !mo.halfDead) {
                if (weakestMonster == null)
                    weakestMonster = mo;
                else if (mo.currentHealth < weakestMonster.currentHealth)
                    weakestMonster = mo;
            }
        }

        if (weakestMonster != null)
            for (int i = 0; i < magicNumber; i++)
                dmgTwo(weakestMonster, AquaLanceMod.Enums.WATER);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}