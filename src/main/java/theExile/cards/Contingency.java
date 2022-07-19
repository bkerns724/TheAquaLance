package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class Contingency extends AbstractExileCard {
    public final static String ID = makeID(Contingency.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;

    public Contingency() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        AutoplayField.autoplay.set(this, true);
        isInnate = true;
        addModifier(elenum.FORCE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}