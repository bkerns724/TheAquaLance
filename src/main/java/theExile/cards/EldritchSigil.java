package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;

public class EldritchSigil extends AbstractExileCard {
    public final static String ID = makeID(EldritchSigil.class.getSimpleName());
    private final static int COST = -2;
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public EldritchSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, ExileMod.Enums.AUTOAIM_ENEMY);
    }

    public void applyAttributes() {
        sigil = true;
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ELDRITCH);
    }

    @Override
    public AbstractMonster getTarget() {
        return Wiz.getLowestHealthEnemy();
    }

    @Override
    public void autoTargetUse(AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m);
    }

    @Override
    public void applyPowers() {
        int temp = baseDamage;
        baseDamage += countCurses() * magicNumber;
        super.applyPowers();
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage += countCurses() * magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = baseDamage != damage;
    }

    public static int countCurses() {
        int counter = 0;

        for (AbstractCard c : adp().masterDeck.group) {
            if (c.type == CardType.CURSE)
                ++counter;
        }

        return counter;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}