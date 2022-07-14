package theExile.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.AttackAction;
import theExile.actions.DiscardToDoAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class SoulFlame extends AbstractExileCard {
    public final static String ID = makeID(SoulFlame.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public SoulFlame() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FIRE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(adp(), damage, DamageInfo.DamageType.NORMAL);
        AbstractGameAction action = new AttackAction(m, info, getAttackEffect());
        atb(new DiscardToDoAction(magicNumber, action, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}