package theExile.cards;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theExile.util.CardArtRoller;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.NONE;
import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.getCardTextureString;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.applyToSelf;

public class BadLuck extends CustomCard {
    public final static String ID = makeID(BadLuck.class.getSimpleName());
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private final static int MAGIC = 2;
    private final static int COST = -2;

    private boolean needsArtRefresh = false;

    public BadLuck() {
        super(ID, NAME, getCardTextureString(BadLuck.class.getSimpleName(), CardType.CURSE), COST, DESCRIPTION,
                CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, NONE);

        if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
            CardArtRoller.computeCard(this);
        } else
            needsArtRefresh = true;
        SoulboundField.soulbound.set(this, true);
        isEthereal = true;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }

    @Override
    public void triggerWhenDrawn() {
        applyToSelf(new DexterityPower(adp(), -magicNumber));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) { }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }
}