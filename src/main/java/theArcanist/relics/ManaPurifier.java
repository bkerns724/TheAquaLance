package theArcanist.relics;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcanist.TheArcanist;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class ManaPurifier extends AbstractArcanistRelic {
    public static final String ID = makeID(ManaPurifier.class.getSimpleName());

    public ManaPurifier() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        setUpdatedDescription();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public boolean canSpawn() {
        return !adp().hasRelic(BlueMarbles.ID);
    }
}
