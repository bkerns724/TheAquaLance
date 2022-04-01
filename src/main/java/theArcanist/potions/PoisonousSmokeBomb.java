package theArcanist.potions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import theArcanist.ArcanistMod;
import theArcanist.VFX.MiasmaEffect;
import theArcanist.actions.PoisonBombAction;

import java.util.Iterator;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class PoisonousSmokeBomb extends AbstractArcanistPotion {
    public static final String POTION_ID = makeID(PoisonousSmokeBomb.class.getSimpleName());
    public static final int DEFAULT_POTENCY = 1;
    public static final PotionRarity RARITY = ArcanistMod.Enums.EVENT;
    public static final PotionSize SIZE = PotionSize.SPHERE;
    public static final boolean IS_THROWN = false;
    public static final boolean TARGET_REQUIRED = false;

    public PoisonousSmokeBomb() {
        super(POTION_ID, RARITY, SIZE, PotionColor.ENERGY,
                IS_THROWN, TARGET_REQUIRED, DEFAULT_POTENCY);
    }

    public void use(AbstractCreature target) {
        if (adRoom().phase == AbstractRoom.RoomPhase.COMBAT)
            atb(new PoisonBombAction());
    }

    public boolean canUse() {
        if (super.canUse()) {
            for (AbstractMonster m : getEnemies()) {
                if (m.hasPower("BackAttack"))
                    return false;
                if (m.type == AbstractMonster.EnemyType.BOSS)
                    return false;
                if (adRoom().eliteTrigger && !adp().hasRelic(SacredBark.ID))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void setDescription() {
        if (potency == 2)
            description = potionStrings.DESCRIPTIONS[1];
        else
            description = potionStrings.DESCRIPTIONS[0];
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
}