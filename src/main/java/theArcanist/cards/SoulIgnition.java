package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;

import static theArcanist.ArcanistMod.makeID;

import static theArcanist.util.Wiz.*;

public class SoulIgnition extends AbstractArcanistCard {
    public final static String ID = makeID(SoulIgnition.class.getSimpleName());
    private final static int DAMAGE = 30;
    private final static int UPGRADE_DAMAGE = 10;
    private final static int MAGIC = 5;
    private final static int COST = 0;
    public static final String MESSAGE_KEY = "SoulIgnitionMessage";
    public static final String CAN_NOT_PLAY_MESSAGE = CardCrawlGame.languagePack.getUIString(
            ArcanistMod.makeID(MESSAGE_KEY)).TEXT[0];

    public SoulIgnition() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.SOUL_FIRE);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        int count = getDebuffCount(m);
        if (count >= 5)
            return true;
        cantUseMessage = CAN_NOT_PLAY_MESSAGE;
        return false;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}