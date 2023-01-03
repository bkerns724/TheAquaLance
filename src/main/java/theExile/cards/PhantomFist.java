package theExile.cards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static theExile.ExileMod.makeID;

public class PhantomFist extends AbstractExileCard {
    public final static String ID = makeID(PhantomFist.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public PhantomFist() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
    }

    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        baseDamage += GameActionManager.totalDiscardedThisTurn * magicNumber;
        super.applyPowers();
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage += GameActionManager.totalDiscardedThisTurn * magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    public void didDiscard() {
        applyPowers();
    }

    public void triggerWhenDrawn() {
        applyPowers();
    }

    public void atTurnStart() {
        resetAttributes();
        applyPowers();
    }

    public AbstractCard makeCopy() {
        AbstractCard tmp = new PhantomFist();
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.applyPowers();
        }

        return tmp;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}