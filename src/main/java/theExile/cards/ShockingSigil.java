package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theExile.ExileMod.makeID;

public class ShockingSigil extends AbstractExileCard {
    public final static String ID = makeID(ShockingSigil.class.getSimpleName());
    private final static int DAMAGE = 14;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int MAGIC = 3;
    private final static int COST = -2;

    public ShockingSigil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.LIGHTNING);
        isMultiDamage = true;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(null, true,
                AbstractDungeon.cardRandomRng);
        calculateCardDamage(mo);
        dmg(mo);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}