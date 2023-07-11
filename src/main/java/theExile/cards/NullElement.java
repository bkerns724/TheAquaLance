package theExile.cards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.CardArtRoller;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.NONE;
import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.getCardTextureString;
import static theExile.util.Wiz.atb;

public class NullElement extends CustomCard {
    public final static String ID = makeID(NullElement.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private final static int COST = -2;
    private boolean needsArtRefresh = false;

    public NullElement() {
        super(ID, NAME, getCardTextureString(NullElement.class.getSimpleName(), CardType.CURSE), COST, DESCRIPTION,
                CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, NONE);
        isEthereal = true;

        if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty())
            CardArtRoller.computeCard(this);
        else
            needsArtRefresh = true;

        SoulboundField.soulbound.set(this, true);
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
        atb(new LoseEnergyAction(1));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
    }
}