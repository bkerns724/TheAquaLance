package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.relics.ManaPurifier;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.getSlashEffect;

public class EnchantedDagger extends AbstractExileCard {
    public final static String ID = makeID(EnchantedDagger.class.getSimpleName());
    private final static int DAMAGE = 12;
    private final static int UPGRADE_DAMAGE = 4;
    private final static int COST = 1;
    public ArrayList<elenum> stableList = new ArrayList<>();

    public EnchantedDagger() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        stableList.addAll(damageModList);
        applyPowers();
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
    }

    public void singleTargetUse(AbstractMonster m) {
        if (damageModList.isEmpty())
            dmg(m, getSlashEffect(damage));
        else
            dmg(m);
    }

    @Override
    public void didDiscard() {
        applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        damageModList.clear();
        DamageModifierManager.clearModifiers(this);
        if (adp().hasRelic(ManaPurifier.ID)) {
            initializeDescription();
            return;
        }
        addModifier(stableList, true);
        for (AbstractCard card : adp().hand.group) {
            if (card instanceof AbstractExileCard && card != this)
                addModifier(((AbstractExileCard) card).damageModList, true);
            if (card instanceof Dualcast) {
                addModifier(elenum.FORCE);
                addModifier(elenum.ICE);
            }
        }
        super.calculateCardDamage(mo);
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        damageModList.clear();
        DamageModifierManager.clearModifiers(this);
        if (adp() == null)
            return;
        if (adp().hasRelic(ManaPurifier.ID)) {
            initializeDescription();
            return;
        }
        addModifier(stableList, true);
        for (AbstractCard card : adp().hand.group) {
            if (card instanceof AbstractExileCard && card != this)
                addModifier(((AbstractExileCard) card).damageModList, true);
            if (card instanceof Dualcast) {
                addModifier(elenum.FORCE);
                addModifier(elenum.ICE);
            }
        }
        super.applyPowers();
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}