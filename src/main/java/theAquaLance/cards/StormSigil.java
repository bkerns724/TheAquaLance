package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.FinisherAction;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import java.util.Iterator;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class StormSigil extends AbstractEasyCard {
    public final static String ID = makeID("StormSigil");
    private final static int DAMAGE = 0;

    public StormSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseDamage = DAMAGE;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void applyPowers() {
        int realBaseDamage = baseDamage;
        int sigilCount = countCards();
        int shardCount = 0;
        for (AbstractMonster m : getEnemies()) {
            shardCount += getShardCount(m);
        }
        baseDamage = shardCount*sigilCount;
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void triggerOnManualDiscard() {
        super.triggerOnManualDiscard();
    }

    public void OnManualDiscard() {
        forAllMonstersLiving((m) -> {
            int shardCount = getShardCount(m);
            int sigilCount = countCards();
            atb(new LoseHPAction(m, adp(), sigilCount*shardCount, AbstractGameAction.AttackEffect.POISON));
            atb(new FinisherAction(m));
        });
    }

    private static int countCards() {
        int count = 0;
        AbstractCard c;

        Iterator var1 = AbstractDungeon.player.hand.group.iterator();
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isSigil(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isSigil(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isSigil(c)) {
                ++count;
            }
        }

        return count;
    }

    private static boolean isSigil(AbstractCard c) { return AbstractCardField.sigil.get(c); }

    public void upp() {
        AbstractCardField.replenish.set(this, true);
    }
}