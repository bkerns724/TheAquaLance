package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class HotSigil extends AbstractExileCard {
    public final static String ID = makeID(HotSigil.class.getSimpleName());
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 3;

    public HotSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        isMultiDamage = true;
        sigil = true;
        addModifier(elenum.FIRE);
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}