package theExile.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.MiniWrathPower;
import theExile.util.CardArtRoller;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.getCardTextureString;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class Wrath extends CustomCard {
    public final static String ID = makeID(Wrath.class.getSimpleName());
    public final static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public final static String NAME = cardStrings.NAME;
    public final static String DESCRIPTION = cardStrings.DESCRIPTION;
    private final static int MAGIC = 1;
    private final static int COST = -2;

    private boolean needsArtRefresh = false;

    public Wrath() {
        super(ID, NAME, getCardTextureString(Wrath.class.getSimpleName(), CardType.CURSE), COST, DESCRIPTION,
                CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;

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
    public void triggerWhenDrawn() {
        applyToSelf(new MiniWrathPower(adp(), magicNumber));
    }

    @Override
    public void upgrade() {
    }
}