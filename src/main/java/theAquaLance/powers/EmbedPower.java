package theAquaLance.powers;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.cards.AbstractEmbedCard;
import theAquaLance.util.TexLoader;

import static theAquaLance.util.Wiz.*;

public class EmbedPower extends AbstractPower implements OnReceivePowerPower {
    public static String POWER_ID = AquaLanceMod.makeID("Embedded");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public AbstractEmbedCard c;

    private static int idOffset = 0;
    private boolean stuck = true;

    public EmbedPower(AbstractCreature owner, AbstractEmbedCard c) {
        ID = POWER_ID + idOffset;
        idOffset++;
        this.c = c;
        name = "Embedded " + c.name;
        type = PowerType.DEBUFF;
        isTurnBased = false;

        this.owner = owner;
        this.amount = -1;

        AquaLanceMod.logger.info(c.getClass().getSimpleName());

        String textureString = AquaLanceMod.modID + "Resources/images/powers/shards/" + c.getClass().getSimpleName() + "32.png";
        Texture normalTexture = TexLoader.getTexture(textureString);
        Texture hiDefImage = TexLoader.getTexture(AquaLanceMod.modID + "Resources/images/powers/shards/" + c.name + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }

    public void unEmbed() {
        if (stuck) {
            stuck = false;
            int randomDest = MathUtils.random(0, 2);
            c.current_x = owner.hb.cX;
            c.current_y = owner.hb.cY;
            switch (randomDest) {  // add animations for each
                case 0:
                    if (AbstractDungeon.player.hand.group.size() >= BaseMod.MAX_HAND_SIZE)
                        adp().hand.moveToDiscardPile(c);
                    else
                        adp().hand.addToRandomSpot(c);
                    break;
                case 1:
                    adp().hand.moveToDiscardPile(c);
                    break;
                case 2:
                    adp().hand.moveToDeck(c, true);
            }
            att(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    public int getShardCount() {
        return c.getPotency(owner);
    }

    public void onManualDiscard() {
        c.onDiscardWhileEmbed(owner);
    }

    @Override
    public void onDeath() {
        c.onDeath();
        unEmbed();
    }

    public void onRemove() {
        unEmbed();
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        c.onReceivePower(power, target, owner);
        return true;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        return c.onAttacked(info, damageAmount, owner);
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        return c.onAttackToChangeDamage(info, damageAmount);
    }

    @Override
    public void atStartOfTurn() {
        c.atStartOfTurn(owner);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        c.atStartOfPlayerTurn(owner);
    }

    @Override
    public void updateDescription() {
        if (c != null)
            description = c.getEmbedDescription();
        else
            description = "Needs a card";
    }
}