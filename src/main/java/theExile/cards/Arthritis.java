package theExile.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.CardArtRoller;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.getCardTextureString;

public class Arthritis extends CustomCard {
    public final static String ID = makeID(Arthritis.class.getSimpleName());
    public final static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public final static String NAME = cardStrings.NAME;
    public final static String DESCRIPTION = cardStrings.DESCRIPTION;
    private final static int COST = -2;

    private boolean needsArtRefresh = false;

    public Arthritis() {
        super(ID, NAME, getCardTextureString(Arthritis.class.getSimpleName(), CardType.CURSE), COST, DESCRIPTION,
                CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);

        if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
            CardArtRoller.computeCard(this);
        } else
            needsArtRefresh = true;
    }

    @Override
    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }
}