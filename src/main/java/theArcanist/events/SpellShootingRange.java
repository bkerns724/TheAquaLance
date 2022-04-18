package theArcanist.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theArcanist.ArcanistMod;
import theArcanist.TheArcanist;
import theArcanist.cards.AbstractArcanistCard;

import java.util.ArrayList;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.adp;

public class SpellShootingRange extends AbstractArcanistEvent {
    public static final String ID = makeID(SpellShootingRange.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.SHRINE;

    private static ArrayList<String> okayRelicList = new ArrayList<>();
    private AbstractRelic commonTradeRelic;
    private AbstractRelic uncommonTradeRelic;
    private boolean pickedDraw = false;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ArcanistMod.makeImagePath("events/" + SpellShootingRange.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = SpellShootingRange.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheCity.ID);
        params.playerClass = TheArcanist.Enums.THE_ARCANIST;
        params.bonusCondition = () -> (hasResonantCard() && hasTradableRelic());
        return params;
    }

    public SpellShootingRange() {
        super(eventStrings, IMAGE_PATH);

        commonTradeRelic = getRelic(AbstractRelic.RelicTier.COMMON, null);
        uncommonTradeRelic = getRelic(AbstractRelic.RelicTier.UNCOMMON, commonTradeRelic);

        if (commonTradeRelic != null && hasResonantCard())
            imageEventText.setDialogOption(options[0].replace("!RelicString!",
                    FontHelper.colorString(commonTradeRelic.name, "r")));
        else
            imageEventText.setDialogOption(options[1], true);

        if (uncommonTradeRelic != null && hasResonantCard())
            imageEventText.setDialogOption(options[2].replace("!RelicString!",
                    FontHelper.colorString(uncommonTradeRelic.name, "r")));
        else
            imageEventText.setDialogOption(options[3], true);

        imageEventText.setDialogOption(options[4]);
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            if (pickedDraw) {
                adp().loseRelic(commonTradeRelic.relicId);
                AbstractArcanistCard c = (AbstractArcanistCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();

                c.extraDraw += 1;
                c.initializeDescription();

                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(),
                        Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH/2.0f, (float)Settings.HEIGHT/2.0F));

                imageEventText.updateBodyText(descriptions[1]);
                imageEventText.clearAllDialogs();
                imageEventText.setDialogOption(options[5]);
                screen = CUR_SCREEN.COMPLETE;
            }
            else {
                adp().loseRelic(uncommonTradeRelic.relicId);
                AbstractArcanistCard c = (AbstractArcanistCard) AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();

                c.extraEnergy += 1;
                c.initializeDescription();

                AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(),
                        Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f));
                AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH/2.0f, (float)Settings.HEIGHT/2.0F));

                imageEventText.updateBodyText(descriptions[2]);
                imageEventText.clearAllDialogs();
                imageEventText.setDialogOption(options[5]);
                screen = CUR_SCREEN.COMPLETE;
            }
        }
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            switch (buttonPressed) {
                case 0:
                    pickedDraw = true;
                    if (!hasResonantCard())
                        break;
                    AbstractDungeon.gridSelectScreen.open(getResonantCards(), 1, descriptions[4], false,
                            false, false, false);
                    break;
                case 1:
                    pickedDraw = false;
                    if (!hasResonantCard())
                        break;
                    AbstractDungeon.gridSelectScreen.open(getResonantCards(), 1, descriptions[4], false,
                            false, false, false);
                    break;
                case 2:
                    screen = CUR_SCREEN.COMPLETE;
                    imageEventText.clearAllDialogs();
                    imageEventText.updateBodyText(descriptions[3]);
                    imageEventText.setDialogOption(options[5]);
            }
        } else
            openMap();
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }

    private static boolean hasResonantCard() {
        for (AbstractCard c : CardGroup.getGroupWithoutBottledCards(adp().masterDeck).group)
            if (c instanceof AbstractArcanistCard && ((AbstractArcanistCard) c).resonant)
                return true;
        return false;
    }

    private static AbstractRelic getRelic(AbstractRelic.RelicTier tier, AbstractRelic reservedRelic) {
        if (okayRelicList == null)
            initializeOkayList();
        ArrayList<AbstractRelic> list = new ArrayList<>();
        for (AbstractRelic relic : adp().relics) {
            if (relic.tier != tier || relic.grayscale || (reservedRelic != null && relic.relicId.equals(reservedRelic.relicId)))
                continue;
            try {
                Class methodClass = relic.getClass().getMethod("onEquip").getDeclaringClass();
                if (methodClass == AbstractRelic.class)
                    list.add(relic);
                else if (okayRelicList != null && okayRelicList.contains(relic.relicId))
                    list.add(relic);
            }
            catch (Exception e) {}
        }

        if (list.size() == 0) {
            if (tier == AbstractRelic.RelicTier.COMMON)
                return getRelic(AbstractRelic.RelicTier.UNCOMMON, reservedRelic);
            else if (tier == AbstractRelic.RelicTier.UNCOMMON)
                return getRelic(AbstractRelic.RelicTier.RARE, reservedRelic);
            else
                return null;
        }
        int x = AbstractDungeon.miscRng.random(0, list.size() - 1);
        return list.get(x);
    }

    private static void initializeOkayList() {
        okayRelicList.add(HappyFlower.ID);
        okayRelicList.add(IncenseBurner.ID);
        okayRelicList.add(DuVuDoll.ID);
    }

    private static boolean hasTradableRelic() {
        return getRelic(AbstractRelic.RelicTier.COMMON, null) != null;
    }

    private static CardGroup getResonantCards() {
        CardGroup resonantGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c :  CardGroup.getGroupWithoutBottledCards(adp().masterDeck).group)
            if (c instanceof AbstractArcanistCard && ((AbstractArcanistCard) c).resonant)
                resonantGroup.addToTop(c);

        return resonantGroup;
    }
}