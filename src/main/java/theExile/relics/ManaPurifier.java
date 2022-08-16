package theExile.relics;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

// Code in AbstractExileCard
public class ManaPurifier extends AbstractExileRelic {
    public static final String ID = makeID(ManaPurifier.class.getSimpleName());

    public ManaPurifier() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        removeModifiers();
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onMasterDeckChange() {
        removeModifiers();
    }

    public void removeModifiers() {
        for (AbstractCard card : adp().masterDeck.group) {
            if (card instanceof AbstractExileCard && card.type == AbstractCard.CardType.ATTACK) {
                AbstractExileCard card2 = (AbstractExileCard) card;
                DamageModifierManager.clearModifiers(card2);
                card2.damageModList.clear();
                card2.initializeDescription();
            }
        }
    }

    @Override
    public boolean canSpawn() {
        return !adp().hasRelic(BlueMarbles.ID);
    }
}
