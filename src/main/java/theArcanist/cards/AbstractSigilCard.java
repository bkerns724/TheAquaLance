package theArcanist.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.patches.AbstractCardPatch;
import theArcanist.patches.DiscardHookPatch;
import theArcanist.powers.AbstractEasyPower;

import java.util.ArrayList;
import java.util.List;

import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractSigilCard extends AbstractArcanistCard {
    public AbstractSigilCard(final String cardID, final CardRarity rarity) {
        super(cardID, -2, CardType.SKILL, rarity, CardTarget.NONE);
        AbstractCardPatch.AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Sigil");
        return retVal;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(BaseMod.getKeywordTitle("arcanistmod:Sigil"),
                BaseMod.getKeywordDescription("arcanistmod:Sigil")));
        return retVal;
    }

    @Override
    public void triggerOnManualDiscard() {
        Integer x = DiscardHookPatch.GameActionManagerField.sigilsThisCombat.get(AbstractDungeon.actionManager);
        DiscardHookPatch.GameActionManagerField.sigilsThisCombat.set(AbstractDungeon.actionManager, x+1);
        onManualDiscard();
        forAllMonstersLiving(m -> {
            for (AbstractPower p : m.powers)
                if (p instanceof AbstractEasyPower)
                    ((AbstractEasyPower) p).onDiscardSigil();
        });
        for (AbstractPower p : adp().powers)
            if (p instanceof AbstractEasyPower)
                ((AbstractEasyPower) p).onDiscardSigil();
    }

    public abstract void onManualDiscard();
}