package theArcanist.relics;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theArcanist.TheArcanist;
import theArcanist.cards.Strike;
import theArcanist.cards.damageMods.DarkDamage;
import theArcanist.cards.damageMods.ForceDamage;
import theArcanist.cards.damageMods.IceDamage;
import theArcanist.cards.damageMods.SoulFireDamage;
import theArcanist.patches.DamageModsIDPatch;

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
            if (card instanceof Strike && ((Strike)card).modifier == null)
                total++;
        if (total > 5)
            total = 5;
        if (total % 2 == 0)
            xOffsetBase += 0.09f;
        for (AbstractCard card : adp().masterDeck.group) {
            if (card instanceof Strike && ((Strike)card).modifier == null) {
                ArrayList<AbstractDamageModifier> list = new ArrayList<>();
                list.add(new DarkDamage());
                list.add(new IceDamage());
                list.add(new SoulFireDamage());
                list.add(new ForceDamage());
                int x = AbstractDungeon.miscRng.random(0, list.size()-1);
                AbstractDamageModifier mod = list.get(x);
                ((Strike) card).modifier = mod;
                ((Strike) card).modifierID = DamageModsIDPatch.ID.get(mod);
                card.baseDamage += 2;
                card.initializeDescription();
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
                        xOffset*Settings.WIDTH, Settings.HEIGHT/2.0f));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(xOffset*Settings.WIDTH, (float)Settings.HEIGHT / 2.0F));
                count++;
            }
        }
    }
}