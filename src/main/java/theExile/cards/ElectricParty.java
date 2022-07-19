package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class ElectricParty extends AbstractExileCard {
    public final static String ID = makeID(ElectricParty.class.getSimpleName());
    private final static int DAMAGE = 34;
    private final static int UPGRADE_DAMAGE = 6;
    private final static int COST = 3;

    public ElectricParty() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        exhaust = true;
        addModifier(elenum.LIGHTNING);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        allDmg();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}