package theExile.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.TheExile;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class HereticBrand extends AbstractExileRelic {
    public static final String ID = makeID(HereticBrand.class.getSimpleName());
    private static final float MAX_HP_PENALTY = 0.2f;

    public HereticBrand() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        int loss = (int) (adp().maxHealth * MAX_HP_PENALTY);
        adp().decreaseMaxHealth(loss);
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
}
