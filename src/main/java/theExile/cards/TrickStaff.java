package theExile.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.forAllMonstersLiving;
import static theExile.util.Wiz.getEnemies;

public class TrickStaff extends AbstractExileCard {
    public final static String ID = makeID(TrickStaff.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;
    private final static int COST = 1;

    public TrickStaff() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void nonTargetUse() {
        forAllMonstersLiving(mon -> {
            calculateCardDamage(mon);
            if (damageModList.isEmpty())
                dmg(mon, Wiz.getBluntEffect(damage));
            else
                dmg(mon);
        });
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        if (mo != null && mo.getIntentBaseDmg() >= 0)
            baseDamage += magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : getEnemies()) {
            if (m != null && m.getIntentBaseDmg() > -0) {
                glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}