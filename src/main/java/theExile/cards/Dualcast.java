package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;
import theExile.actions.AttackAction;
import theExile.damagemods.ForceDamage;
import theExile.damagemods.IceDamage;
import theExile.relics.ManaPurifier;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class Dualcast extends AbstractExileCard {
    public final static String ID = makeID(Dualcast.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Dualcast() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (adp() != null && adp().hasRelic(ManaPurifier.ID)) {
            dmg(m);
            dmg(m);
            return;
        }

        DamageInfo info_1 = new DamageInfo(adp(), damage, damageTypeForTurn);
        DamageModifierManager.bindDamageMods(info_1, new DamageModContainer(this, new IceDamage()));
        DamageInfo info_2 = new DamageInfo(adp(), damage, damageTypeForTurn);
        DamageModifierManager.bindDamageMods(info_2, new DamageModContainer(this, new ForceDamage()));

        AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.NONE;

        if (!damageModList.isEmpty()) {
            if (damage < DAMAGE_THRESHOLD_M)
                effect = ExileMod.Enums.DARK_WAVE;
            else if (damage < DAMAGE_THRESHOLD_L)
                effect = ExileMod.Enums.DARK_WAVE_M;
            else
                effect = ExileMod.Enums.DARK_WAVE_L;

            dmg(m);
            dmg(m);
            return;
        }

        if (damage < DAMAGE_THRESHOLD_M)
            effect = ExileMod.Enums.ICE;
        else if (damage < DAMAGE_THRESHOLD_L)
            effect = ExileMod.Enums.ICE_M;
        else
            effect = ExileMod.Enums.ICE_L;
        atb(new AttackAction(m, info_1, effect));

        if (damage < DAMAGE_THRESHOLD_M)
            effect = ExileMod.Enums.FORCE;
        else if (damage < DAMAGE_THRESHOLD_L)
            effect = ExileMod.Enums.FORCE_M;
        else
            effect = ExileMod.Enums.FORCE_L;
        atb(new AttackAction(m, info_2, effect));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}