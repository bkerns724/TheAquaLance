package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.SoulFireDamage;

import static theArcanist.util.Wiz.*;

import static theArcanist.ArcanistMod.makeID;

public class BurningSigil extends AbstractSigilCard {
    public final static String ID = makeID("BurningSigil");
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public BurningSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
        DamageModifierManager.addModifier(this, new SoulFireDamage());
    }

    @Override
    public void onManualDiscard() {
        allDmg(ArcanistMod.Enums.SOUL_FIRE);
        atb(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}