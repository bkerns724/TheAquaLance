package theArcanist.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theArcanist.TheArcanist;
import theArcanist.cards.AbstractArcanistCard;
import theArcanist.cards.Strike;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

public class EnchantmentOils extends AbstractArcanistRelic {
    public static final String ID = makeID(EnchantmentOils.class.getSimpleName());
    public static final int DAMAGE_BONUS = 2;

    public EnchantmentOils() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, TheArcanist.Enums.ARCANIST_BLARPLE_COLOR);
        amount = DAMAGE_BONUS;
        setUpdatedDescription();
    }

    @Override
    public void onEquip() {
        int count = 0;
        float xOffsetBase = 0.5f;
        float xOffset = xOffsetBase;
        int total = 0;
        for (AbstractCard card : adp().masterDeck.group)
            if (card instanceof Strike && ((Strike)card).damageModList.isEmpty())
                total++;
        if (total > 5)
            total = 5;
        if (total % 2 == 0)
            xOffsetBase += 0.09f;
        for (AbstractCard card : adp().masterDeck.group) {
            if (card instanceof Strike) {
                ArrayList<AbstractArcanistCard.elenum> list = new ArrayList<>();
                if (((Strike) card).damageModList.contains(AbstractArcanistCard.elenum.ICE))
                    list.add(AbstractArcanistCard.elenum.ICE);
                if (((Strike) card).damageModList.contains(AbstractArcanistCard.elenum.FIRE))
                    list.add(AbstractArcanistCard.elenum.FIRE);
                if (((Strike) card).damageModList.contains(AbstractArcanistCard.elenum.FORCE))
                    list.add(AbstractArcanistCard.elenum.FORCE);
                if (((Strike) card).damageModList.contains(AbstractArcanistCard.elenum.DARK))
                    list.add(AbstractArcanistCard.elenum.DARK);
                if (list.size() > 0) {
                    int x = AbstractDungeon.miscRng.random(0, list.size() - 1);
                    AbstractArcanistCard.elenum mod = list.get(x);
                    card.baseDamage += DAMAGE_BONUS;
                    ((Strike) card).addModifier(mod);
                    if (count == 0)
                        xOffset = xOffsetBase;
                    else if (count == 1)
                        xOffset = xOffsetBase - 0.18f;
                    else if (count == 2)
                        xOffset = xOffsetBase + 0.18f;
                    else if (count == 3)
                        xOffset = xOffsetBase - 0.36f;
                    else if (count == 4)
                        xOffset = xOffsetBase + 0.36f;
                    else if (count >= 5)
                        continue;
                    AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(card.makeStatEquivalentCopy(),
                            xOffset * Settings.WIDTH, Settings.HEIGHT / 2.0f));
                    AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(xOffset * Settings.WIDTH, (float) Settings.HEIGHT / 2.0F));
                    count++;
                }
            }
        }
    }
}