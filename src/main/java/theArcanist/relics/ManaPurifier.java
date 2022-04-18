package theArcanist.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

// Code in AbstractArcanistCard
public class ManaPurifier extends AbstractArcanistRelic {
    public static final String ID = makeID(ManaPurifier.class.getSimpleName());

    public ManaPurifier() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        // removeModifiers();
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
/*
    @Override
    public void onMasterDeckChange() {
        removeModifiers();
    }

    public void removeModifiers() {
        for (AbstractCard card : adp().masterDeck.group) {
            if (card instanceof AbstractArcanistCard) {
                AbstractArcanistCard card2 = (AbstractArcanistCard) card;
                DamageModifierManager.clearModifiers(card2);
                card2.damageModList.clear();
                card2.initializeDescription();
            }
        }
    }

 */

    @Override
    public boolean canSpawn() {
        return !adp().hasRelic(BlueMarbles.ID);
    }
}
