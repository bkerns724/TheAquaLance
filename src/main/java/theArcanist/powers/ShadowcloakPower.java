package theArcanist.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theArcanist.util.Wiz.*;
import theArcanist.ArcanistMod;
import theArcanist.relics.TatteredCloak;
import theArcanist.relics.TransparentRing;

public class ShadowcloakPower extends AbstractArcanistPower {
    public static String POWER_ID = ArcanistMod.makeID(ShadowcloakPower.class.getSimpleName());
    public static final int DAMAGE_MULT_DESC = 25;
    public static final float DAMAGE_MULT = DAMAGE_MULT_DESC/100.0f;
    private boolean triggered;

    public ShadowcloakPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        priority = 98;
        triggered = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(c -> c.type == AbstractCard.CardType.ATTACK);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && !triggered) {
            if (adp().hasRelic(TatteredCloak.ID))
                damage *= (1.0f + TatteredCloak.CLOAK_MULT * amount);
            else
                damage *= (1.0f + DAMAGE_MULT * amount);
        }
        return damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && !triggered) {
            flash();
            triggered = true;
        }
        else if (adp().hasRelic(TransparentRing.ID) && card.baseBlock > 0 && !card.cardID.equals(RitualDagger.ID)) {
            flash();
            triggered = true;
        }
    }

    public float modifyBlock(float blockAmount) {
        if (!adp().hasRelic(TransparentRing.ID) || blockAmount <= 0)
            return blockAmount;
        return (blockAmount * (1.0f + DAMAGE_MULT*amount));
    }

    @Override
    public void atStartOfTurn() {
        triggered = false;
    }
}