package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.damagemods.ScourgeType;

import static theExile.ExileMod.makeID;

public class SpellGauntlet extends AbstractExileCard {
    public final static String ID = makeID(SpellGauntlet.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public SpellGauntlet() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        magicOneIsDebuff = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, getBluntEffect());
        int amount = getJinxAmountCard(m);
        if (amount > 0) {
            baseBlock = amount;
            applyPowers();
            blck();
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}