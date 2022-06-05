package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.JinxPower;
import theArcanist.vfx.DarkWaveEffect;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChanneledCurse extends AbstractArcanistCard {
    public final static String ID = makeID(ChanneledCurse.class.getSimpleName());
    public final static String LOC_NAME = CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    private final static int DAMAGE = 8;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ChanneledCurse() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        resonant = true;
        magicOneIsDebuff = true;
        jinx = baseMagicNumber;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (p != null && m != null)
            vfx(new DarkWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F);
        dmg(m);
        applyToEnemy(m, new JinxPower(m, magicNumber));
    }

    @Override
    protected AbstractGameAction.AttackEffect getDefaultAttackEffect() {
        if (damage > 15)
            return AbstractGameAction.AttackEffect.SLASH_HEAVY;
        else
            return getRandomSlash();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}