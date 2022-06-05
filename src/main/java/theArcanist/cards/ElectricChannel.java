package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.vfx;

public class ElectricChannel extends AbstractArcanistCard {
    public final static String ID = makeID(ElectricChannel.class.getSimpleName());
    public final static String LOC_NAME = CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public ElectricChannel() {
        super(ID, COST, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        resonant = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ORB_LIGHTNING_EVOKE"));
        vfx(new LightningEffect(m.drawX, m.drawY), 0f);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}