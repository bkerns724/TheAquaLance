package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.DuVuDoll;
import com.megacrit.cardcrawl.relics.IncenseBurner;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.patches.TipsInDialogPatch;
import theExile.potions.SteelhidePotion;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class MarketActThree extends AbstractExileEvent {
    public static final String ID = makeID(MarketActThree.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.NORMAL;
    private static final float HP_LOSS_A0 = 0.25f;
    private static final float HP_LOSS_A15 = 0.35f;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;
    private AbstractRelic relicForTrade = null;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + MarketActThree.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = MarketActThree.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheBeyond.ID);
        params.playerClass = TheExile.Enums.THE_EXILE;
        params.bonusCondition = () -> (!adp().hasRelic(Sozu.ID));

        return params;
    }

    public MarketActThree() {
        super(eventStrings, IMAGE_PATH, getHpLoss());
        relicForTrade = getRelic();

        if (relicForTrade != null) {
            String modifiedOption = options[0].replace("!RelicString!", FontHelper.colorString(relicForTrade.name, "r"));
            modifiedOption = modifiedOption.replace("!PotionString!", FontHelper.colorString(new SteelhidePotion().name, "g"));
            imageEventText.setDialogOption(modifiedOption);
            LargeDialogOptionButton but = imageEventText.optionList.get(0);
            TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, getTips(relicForTrade));
        }
        else
            imageEventText.setDialogOption(options[1], true);

        imageEventText.setDialogOption(options[2].replace("!PotionString!", FontHelper.colorString(new SteelhidePotion().name, "g")));
        LargeDialogOptionButton but2 = imageEventText.optionList.get(1);
        TipsInDialogPatch.ButtonPreviewField.previewTips.set(but2, getTips(null));

        imageEventText.setDialogOption(options[3]);
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            screen = CUR_SCREEN.COMPLETE;
            switch (buttonPressed) {
                case 0:
                    adp().loseRelic(relicForTrade.relicId);
                    AbstractDungeon.getCurrRoom().rewards.clear();
                    AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new SteelhidePotion()));
                    AbstractDungeon.combatRewardScreen.open();
                    imageEventText.updateBodyText(descriptions[1]);
                    break;
                case 1:
                    int x = AbstractDungeon.miscRng.random(0, 1);
                    if (x == 0) {
                        AbstractDungeon.getCurrRoom().rewards.clear();
                        AbstractDungeon.getCurrRoom().rewards.add(new RewardItem(new SteelhidePotion()));
                        AbstractDungeon.combatRewardScreen.open();
                        imageEventText.updateBodyText(descriptions[2]);
                    }
                    else {
                        AbstractDungeon.player.damage(new DamageInfo(null, amount));
                        imageEventText.updateBodyText(descriptions[3]);
                    }
                    break;
                case 2:
                    imageEventText.updateBodyText(descriptions[4]);
                    break;
            }
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[4]);
        } else
            openMap();
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }

    private static int getHpLoss() {
        if (AbstractDungeon.ascensionLevel < 15)
            return (int)(HP_LOSS_A0*adp().maxHealth);
        else
            return (int)(HP_LOSS_A15*adp().maxHealth);
    }

    private static ArrayList<PowerTip> getTips(AbstractRelic relic) {
        ArrayList<PowerTip> list = new ArrayList<>();
        if (relic != null)
            list.addAll(relic.tips);
        list.addAll(new SteelhidePotion().tips);
        return list;
    }

    private static AbstractRelic getRelic() {
        ArrayList<AbstractRelic> list = new ArrayList<>();
        for (AbstractRelic relic : adp().relics) {
            if (relic.tier != AbstractRelic.RelicTier.RARE || relic.grayscale)
                continue;
            try {
                Class methodClass = relic.getClass().getMethod("onEquip").getDeclaringClass();
                if (methodClass == AbstractRelic.class)
                    list.add(relic);
                else if (relic.relicId.equals(IncenseBurner.ID) || relic.relicId.equals(DuVuDoll.ID))
                    list.add(relic);
            }
            catch (Exception e) {}
        }

        if (list.size() == 0)
            return null;
        int x = AbstractDungeon.miscRng.random(0, list.size() - 1);
        return list.get(x);
    }
}