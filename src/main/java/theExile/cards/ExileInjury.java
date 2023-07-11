package theExile.cards;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.getCardTextureString;

@NoCompendium
public class ExileInjury extends CustomCard {
    public final static String ID = makeID(ExileInjury.class.getSimpleName());
    public final static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public final static String NAME = cardStrings.NAME;
    public final static String DESCRIPTION = cardStrings.DESCRIPTION;
    private final static int COST = -2;
    private final static String imgUrl = "curse/injury";

    public ExileInjury() {
        super(ID, NAME, getCardTextureString(Wrath.class.getSimpleName(), CardType.CURSE), COST, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        SoulboundField.soulbound.set(this, true);
        TextureAtlas atl = ReflectionHacks.getPrivateStatic(AbstractCard.class, "cardAtlas");
        assetUrl = imgUrl;
        portrait = atl.findRegion(imgUrl);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new ExileInjury();
    }
}