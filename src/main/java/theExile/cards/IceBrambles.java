package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.damagemods.ScourgeType;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.getJinxAmount;

public class IceBrambles extends AbstractExileCard {
    public final static String ID = makeID(IceBrambles.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int COST = 2;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 2;

    public IceBrambles() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        DamageModifierManager.addModifier(this, new ScourgeType());
        addModifier(elenum.ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int temp = baseDamage;
        baseDamage += magicNumber*getJinxAmount(mo);
        super.calculateCardDamage(mo);
        baseDamage = temp;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}