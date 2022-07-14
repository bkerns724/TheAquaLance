package theExile.events;

import basemod.eventUtil.AddEventParams;
import basemod.eventUtil.EventUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.ui.buttons.LargeDialogOptionButton;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theExile.ExileMod;
import theExile.TheExile;
import theExile.cards.AbstractExileCard;
import theExile.patches.TipsInDialogPatch;
import theExile.relics.ChemicalZ;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.adp;

public class LadyInRed extends AbstractExileEvent {
    public static final String ID = makeID(LadyInRed.class.getSimpleName());
    private static final EventStrings eventStrings;
    private static final String IMAGE_PATH;
    private static final EventUtils.EventType TYPE = EventUtils.EventType.SHRINE;

    private CUR_SCREEN screen = CUR_SCREEN.INTRO;

    private static final float HP_A0 = 0.2f;
    private static final float HP_A15 = 0.25f;
    private static final float MAX_HP_A0 = 0.1f;
    private static final float MAX_HP_A15 = 0.15f;
    private static final int DEBUFF_COUNT = 3;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        IMAGE_PATH = ExileMod.makeImagePath("events/" + LadyInRed.class.getSimpleName() + ".jpg");
    }

    public static AddEventParams getParams() {
        AddEventParams params = new AddEventParams();
        params.eventClass = LadyInRed.class;
        params.eventID = ID;
        params.eventType = TYPE;
        params.dungeonIDs = new ArrayList<>();
        params.dungeonIDs.add(TheCity.ID);
        params.playerClass = TheExile.Enums.THE_EXILE;
        params.bonusCondition = () -> (adp().currentHealth > getHpLoss());
        return params;
    }

    public LadyInRed() {
        super(eventStrings, IMAGE_PATH, getHpLoss(), getMaxHpLoss());

        ChemicalZ chem = new ChemicalZ();
        imageEventText.setDialogOption(options[0].replace("!RelicString!",
                FontHelper.colorString(chem.name, "g")), chem);
        if (hasDebuffCard() && adp().currentHealth > amount) {
            imageEventText.setDialogOption(options[1].replace("!dCount!", "" + DEBUFF_COUNT));
            LargeDialogOptionButton but = imageEventText.optionList.get(1);
            TipsInDialogPatch.ButtonPreviewField.previewTips.set(but, getTipsDebuff());
        }
        else
            imageEventText.setDialogOption(options[2], true);
        imageEventText.setDialogOption(options[3]);
    }

    public void update() {
        super.update();
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            int count = 0;
            float xOffsetBase = 0.5f;
            float xOffset = xOffsetBase;
            int total = AbstractDungeon.gridSelectScreen.selectedCards.size();
            if (total > 5)
                total = 5;
            if (total % 2 == 0)
                xOffsetBase += 0.09f;
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractExileCard arCard = ((AbstractExileCard) card);
                if (arCard.magicOneIsDebuff) {
                    arCard.baseMagicNumber *= 2;
                    arCard.magicNumber *= 2;
                }
                if (arCard.magicTwoIsDebuff) {
                    arCard.baseSecondMagic *= 2;
                    arCard.secondMagic *= 2;
                }
                arCard.debuffIncrease = true;
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
            imageEventText.updateBodyText(descriptions[2]);

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            screen = CUR_SCREEN.COMPLETE;
            imageEventText.clearAllDialogs();
            imageEventText.setDialogOption(options[3]);
        }
    }

    protected void buttonEffect(int buttonPressed) {
        if (screen == CUR_SCREEN.INTRO) {
            screen = CUR_SCREEN.COMPLETE;
            switch (buttonPressed) {
                case 0:
                    AbstractDungeon.player.damage(new DamageInfo(null, getHpLoss()));
                    adRoom().spawnRelicAndObtain((float) Settings.WIDTH * 0.28F,
                            (float) Settings.HEIGHT / 2.0F, new ChemicalZ());
                    imageEventText.updateBodyText(descriptions[1]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[3]);
                    break;
                case 1:
                    adp().decreaseMaxHealth(amount2);
                    CardGroup debuffGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    for (AbstractCard c : adp().masterDeck.group)
                        if (c instanceof AbstractExileCard &&
                                (((AbstractExileCard) c).magicOneIsDebuff || ((AbstractExileCard)c).magicTwoIsDebuff ))
                            debuffGroup.addToTop(c);
                    int y = Math.min(debuffGroup.size(), DEBUFF_COUNT);
                    AbstractDungeon.gridSelectScreen.open(debuffGroup, y, descriptions[4], false,
                            false, false, false);
                    break;
                case 2:
                    imageEventText.updateBodyText(descriptions[3]);
                    imageEventText.clearAllDialogs();
                    imageEventText.setDialogOption(options[3]);
                    break;
            }
        } else
            openMap();
    }

    private enum CUR_SCREEN {
        INTRO,
        COMPLETE;
    }

    private static int getHpLoss() {
        if (AbstractDungeon.ascensionLevel < 15)
            return (int)(HP_A0*adp().maxHealth);
        return (int)(HP_A15*adp().maxHealth);
    }

    private static int getMaxHpLoss() {
        if (AbstractDungeon.ascensionLevel < 15)
            return (int)(MAX_HP_A0*adp().maxHealth);
        return (int)(MAX_HP_A15*adp().maxHealth);
    }

    private static boolean hasDebuffCard() {
        for (AbstractCard c : adp().masterDeck.group)
            if (c instanceof AbstractExileCard &&
                    (((AbstractExileCard) c).magicOneIsDebuff || ((AbstractExileCard)c).magicTwoIsDebuff ))
                return true;
        return false;
    }

    private ArrayList<PowerTip> getTipsDebuff() {
        ArrayList<PowerTip> list = new ArrayList<>();
        list.add(new PowerTip(descriptions[5], descriptions[6]));
        return list;
    }
}