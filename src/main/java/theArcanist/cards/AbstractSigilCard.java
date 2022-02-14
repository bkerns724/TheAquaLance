package theArcanist.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.ArcanistMod;
import theArcanist.powers.AbstractArcanistPower;

import java.util.ArrayList;
import java.util.List;

import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractSigilCard extends AbstractArcanistCard {
    public boolean beingDiscarded = false;
    public static final String MESSAGE_KEY = "SigilMessage";
    public static final String CAN_NOT_PLAY_MESSAGE = CardCrawlGame.languagePack.getUIString(
            ArcanistMod.makeID(MESSAGE_KEY)).TEXT[0];

    public AbstractSigilCard(final String cardID, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, -2, type, rarity, target);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onManualDiscard();
        beingDiscarded = false;
    }

    public abstract void onManualDiscard();

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean superBool = super.canUse(p, m);
        if (!superBool) {
            beingDiscarded = false;
            return false;
        }
        if (!beingDiscarded) {
            cantUseMessage = CAN_NOT_PLAY_MESSAGE;
            return false;
        }

        return true;
    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Sigil");
        return retVal;
    }

    @Override
    public void triggerOnManualDiscard() {
        beingDiscarded = true;
        autoPlayWhenDiscarded();
        // Powers that trigger on discard
        forAllMonstersLiving(m -> {
            for (AbstractPower pow : m.powers)
                if (pow instanceof AbstractArcanistPower)
                    ((AbstractArcanistPower) pow).onDiscardSigil();
        });
        for (AbstractPower pow : adp().powers) {
            if (pow instanceof AbstractArcanistPower)
                ((AbstractArcanistPower) pow).onDiscardSigil();
        }
    }

    protected void autoPlayWhenDiscarded() {
        AbstractDungeon.player.discardPile.removeCard(this);
        AbstractDungeon.getCurrRoom().souls.remove(this);
        AbstractDungeon.player.limbo.group.add(this);
        target_y = Settings.HEIGHT / 2.0f;
        target_x = Settings.WIDTH / 2.0f;
        targetAngle = 0;
        targetDrawScale = 0.8f;
        lighten(true);
        att(new NewQueueCardAction(this, true, false, true));
        att(new UnlimboAction(this));
    }
}