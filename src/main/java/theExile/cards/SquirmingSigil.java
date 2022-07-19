package theExile.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.BlackSigilAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.atb;
import static theExile.util.Wiz.getLowestHealthEnemy;

public class SquirmingSigil extends AbstractExileCard {
    public final static String ID = makeID(SquirmingSigil.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public SquirmingSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.DARK);
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster weakestMonster = getLowestHealthEnemy();
        calculateCardDamage(weakestMonster);

        DamageInfo info = new DamageInfo(p, damage, damageTypeForTurn);
        AbstractGameAction.AttackEffect effect = this.getAttackEffect();

        atb(new BlackSigilAction(weakestMonster, info, magicNumber, effect));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}