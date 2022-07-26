package theExile.cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class Thunderbolt extends AbstractExileCard {
    public final static String ID = makeID(Thunderbolt.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 3;
    private final static int COST = 1;

    public Thunderbolt() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m);
        atb(new DamageAction(adp(), new DamageInfo(adp(), magicNumber, DamageInfo.DamageType.NORMAL)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}