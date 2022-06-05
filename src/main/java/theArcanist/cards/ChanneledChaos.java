package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.actions.ChaosMagicAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.getRandomSlash;

public class ChanneledChaos extends AbstractArcanistCard {
    public final static String ID = makeID(ChanneledChaos.class.getSimpleName());
    public final static String LOC_NAME = CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    private final static int DAMAGE = 8;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int SECOND_MAGIC = 1;
    private final static int COST = 1;

    public ChanneledChaos() {
        super(ID, COST, CardType.ATTACK, ArcanistMod.Enums.UNIQUE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
        chaos = baseSecondMagic;
        resonant = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        atb(new ChaosMagicAction());
    }

    @Override
    protected AbstractGameAction.AttackEffect getDefaultAttackEffect() {
        if (damage > 15)
            return AbstractGameAction.AttackEffect.SLASH_HEAVY;
        else
            return getRandomSlash();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}