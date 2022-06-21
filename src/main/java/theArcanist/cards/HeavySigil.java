package theArcanist.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.HeavySigilAction;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.atb;
import static theArcanist.util.Wiz.getHighestHealthEnemy;

public class HeavySigil extends AbstractArcanistCard {
    public final static String ID = makeID(HeavySigil.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 20;

    public HeavySigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        isMultiDamage = true;
        sigil = true;
        addModifier(elenum.FORCE);
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster strongestMonster = getHighestHealthEnemy();
        calculateCardDamage(strongestMonster);

        DamageInfo info = new DamageInfo(p, damage, damageTypeForTurn);

        atb(new HeavySigilAction(strongestMonster, info, magicNumber, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}