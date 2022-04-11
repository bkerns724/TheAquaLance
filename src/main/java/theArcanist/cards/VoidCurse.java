package theArcanist.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.util.CardArtRoller;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.NONE;
import static theArcanist.ArcanistMod.makeID;
import static theArcanist.cards.AbstractArcanistCard.getCardTextureString;

public class VoidCurse extends CustomCard {
    public final static String ID = makeID(VoidCurse.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private final static int COST = -2;
    private boolean needsArtRefresh = false;

    public VoidCurse() {
        super(ID, NAME, getCardTextureString(VoidCurse.class.getSimpleName(), CardType.CURSE), COST, DESCRIPTION,
                CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, NONE);
        isEthereal = true;

        if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
            CardArtRoller.computeCard(this);
        } else
            needsArtRefresh = true;
    }

    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void triggerWhenDrawn() {
        this.addToBot(new LoseEnergyAction(1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
    }
}