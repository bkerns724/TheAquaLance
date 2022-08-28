package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.AttackAction;
import theExile.damagemods.FakeForceDamage;
import theExile.damagemods.FakeIceDamage;
import theExile.damagemods.ForceDamage;
import theExile.damagemods.IceDamage;
import theExile.relics.ManaPurifier;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.*;

public class Dualcast extends AbstractExileCard {
    public final static String ID = makeID(Dualcast.class.getSimpleName());
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public Dualcast() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(FAKE_ICE);
        addModifier(FAKE_FORCE);
    }

    public void singleTargetUse(AbstractMonster m) {
        if (adp() != null && adp().hasRelic(ManaPurifier.ID)) {
            dmg(m);
            dmg(m);
            return;
        }

        if (!damageModList.contains(elenum.ICE)) {
            DamageInfo info = new DamageInfo(adp(), damage, damageTypeForTurn);
            DamageModifierManager.bindDamageMods(info, new DamageModContainer(this, new IceDamage()));
            if (damageModList.isEmpty())
                atb(new AttackAction(m, info, getIceEffect(damage)));
            else
                atb(new AttackAction(m, info, getDarkWaveEffect(damage)));
        } else
            dmg(m);

        if (!damageModList.contains(elenum.FORCE)) {
            DamageInfo info = new DamageInfo(adp(), damage, damageTypeForTurn);
            DamageModifierManager.bindDamageMods(info, new DamageModContainer(this, new ForceDamage()));
            if (damageModList.isEmpty())
                atb(new AttackAction(m, info, getForceEffect(damage)));
            else
                atb(new AttackAction(m, info, getDarkWaveEffect(damage)));
        } else
            dmg(m);
    }

    @Override
    public void initializeDescription() {
        if (damageModList == null)
            damageModList = new ArrayList<>();
        if (damageModList.contains(ELDRITCH)) {
            rawDescription = rawDescription.replace("!Icon1! ", "!Icon1! " + ELDRITCH_STRING + " ");
            rawDescription = rawDescription.replace("!Icon2! ", "!Icon2! " + ELDRITCH_STRING + " ");
        }
        if (damageModList.contains(LIGHTNING)) {
            rawDescription = rawDescription.replace("!Icon1! ", "!Icon1! " + LIGHTNING_STRING + " ");
            rawDescription = rawDescription.replace("!Icon2! ", "!Icon2! " + LIGHTNING_STRING + " ");
        }
        if (damageModList.contains(FORCE))
            rawDescription = rawDescription.replace("!Icon1! ", "!Icon1! " + FORCE_STRING + " ");
        if (damageModList.contains(ICE))
            rawDescription = rawDescription.replace("!Icon2! ", "!Icon2! " + COLD_STRING + " ");

        rawDescription = rawDescription.replace("!Icon1! ", COLD_STRING);
        rawDescription = rawDescription.replace("!Icon2! ", FORCE_STRING);

        if (selfRetain)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[0] + rawDescription;
        if (isInnate)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[11] + rawDescription;
        if (isEthereal)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[10] + rawDescription;
        if (sigil)
            rawDescription = thisCardStrings.EXTENDED_DESCRIPTION[1] + rawDescription;

        if (exhaust)
            rawDescription = rawDescription + thisCardStrings.EXTENDED_DESCRIPTION[7];

        super.initializeDescription();
    }

    @Override
    public void addModifier(elenum element, boolean tips) {
        if (element == elenum.ICE) {
            for (AbstractDamageModifier mod : DamageModifierManager.modifiers(this))
                if (mod instanceof FakeIceDamage)
                    DamageModifierManager.removeModifier(this, mod);
            damageModList.remove(FAKE_ICE);
        }
        if (element == elenum.FORCE) {
            for (AbstractDamageModifier mod : DamageModifierManager.modifiers(this))
                if (mod instanceof FakeForceDamage)
                    DamageModifierManager.removeModifier(this, mod);
            damageModList.remove(FAKE_FORCE);
        }
        super.addModifier(element, tips);
    }

    @Override
    public void nonTargetUse() {
        discard(magicNumber);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}