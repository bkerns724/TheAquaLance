package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.SackOfDebrisAction;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class SackOfDebris extends AbstractExileCard {
    public final static String ID = makeID(SackOfDebris.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public SackOfDebris() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
        selfRetain = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new SackOfDebrisAction(this));
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        baseDamage = (adp().hand.size() - 1) * magicNumber;
        super.applyPowers();
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage = (adp().hand.size() - 1) * magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}