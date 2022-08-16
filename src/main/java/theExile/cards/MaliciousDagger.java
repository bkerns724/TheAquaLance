package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;

public class MaliciousDagger extends AbstractExileCard {
    public final static String ID = makeID(MaliciousDagger.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int COST = 0;

    public MaliciousDagger() {
        super(ID, COST, CardType.ATTACK, ExileMod.Enums.UNIQUE, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        int count = getCurseCount();
        for (int i = 0; i < count; i++)
            dmg(m, getSlashEffect());
    }

    private int getCurseCount() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group)
            if (c.type == CardType.CURSE)
                count++;
        return count;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}