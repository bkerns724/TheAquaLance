package theExile.cards;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import theExile.util.CardArtRoller;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.getCardTextureString;
import static theExile.util.Wiz.*;

public class Whispers extends CustomCard {
    public final static String ID = makeID(Whispers.class.getSimpleName());
    public final static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public final static String NAME = cardStrings.NAME;
    public final static String DESCRIPTION = cardStrings.DESCRIPTION;
    private final static int MAGIC = 1;
    private final static int COST = -2;

    private boolean needsArtRefresh = false;

    public Whispers() {
        super(ID, NAME, getCardTextureString(Whispers.class.getSimpleName(), CardType.CURSE), COST, DESCRIPTION,
                CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;

        if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
            CardArtRoller.computeCard(this);
        } else
            needsArtRefresh = true;
    }

    public void triggerWhenDrawn() {
        atb(new SetDontTriggerAction(this, false));
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override
    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (dontTriggerOnUseCard) {
            DrawReductionPower power = new DrawReductionPower(adp(), magicNumber);
            ReflectionHacks.setPrivate(power, DrawReductionPower.class, "justApplied", false);
            applyToSelfTop(power);
        }
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }
}