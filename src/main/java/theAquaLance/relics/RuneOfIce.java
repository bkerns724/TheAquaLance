package theAquaLance.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theAquaLance.TheAquaLance;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.srcCommonCardPool;
import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class RuneOfIce extends AbstractEasyRelic {
    public static final String ID = makeID("RuneOfIce");

    public RuneOfIce() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK, TheAquaLance.Enums.TURQUOISE_COLOR);
    }


    public void atBattleStartPreDraw() {
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        
        ArrayList<AbstractCard> list = new ArrayList<>();
        Iterator var2 = srcCommonCardPool.group.iterator();
        AbstractCard c;
        while(var2.hasNext()) {
            c = (AbstractCard)var2.next();
            if (AbstractCardField.sigil.get(c))
                list.add(c);
        }
        AbstractCard newCard = list.get(cardRandomRng.random(list.size() - 1));

        atb(new MakeTempCardInDrawPileAction(newCard, 1, true, true));
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
