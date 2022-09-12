package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.AttackAction;
import theExile.damagemods.FakeIceDamage;
import theExile.damagemods.FakePhantasmalDamage;
import theExile.damagemods.IceDamage;
import theExile.damagemods.PhantasmalDamage;
import theExile.relics.ManaPurifier;

import java.util.ArrayList;

import static theExile.ExileMod.makeID;
import static theExile.cards.AbstractExileCard.elenum.*;
import static theExile.util.Wiz.*;

public class DualCast extends AbstractExileCard {
    public final static String ID = makeID(DualCast.class.getSimpleName());
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 2;
    private final static int MAGIC = 1;
    private final static int COST = 2;

    public DualCast() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(FAKE_ICE);
        addModifier(FAKE_PHANTASMAL);
    }

    public void singleTargetUse(AbstractMonster m) {
        if (adp() != null && adp().hasRelic(ManaPurifier.ID)) {
            dmg(m);
            dmg(m);
            return;
        }

        if (!damageModList.contains(elenum.ICE)) {
            AbstractGameAction.AttackEffect effect;
            DamageModContainer iceContainer = new DamageModContainer(this, new IceDamage());
            if (damageModList.contains(PHANTASMAL) || damageModList.contains(LIGHTNING) || damageModList.contains(ELDRITCH))
                effect = getDarkWaveEffect(damage);
            else
                effect = getIceEffect(damage);
            DamageInfo info = BindingHelper.makeInfo(iceContainer, adp(), damage, damageTypeForTurn);
            atb(new AttackAction(m, info, effect));
        } else
            dmg(m);

        if (!damageModList.contains(elenum.PHANTASMAL)) {
            AbstractGameAction.AttackEffect effect;
            DamageModContainer phantasmalContainer = new DamageModContainer(this, new PhantasmalDamage());
            if (damageModList.contains(ICE) || damageModList.contains(LIGHTNING) || damageModList.contains(ELDRITCH))
                effect = getDarkWaveEffect(damage);
            else
                effect = getPhantasmalEffect(damage);
            DamageInfo info = BindingHelper.makeInfo(phantasmalContainer, adp(), damage, damageTypeForTurn);
            atb(new AttackAction(m, info, effect));
        } else
            dmg(m);
    }

    @Override
    public void initDesc() {
        if (cardStrings == null)
            cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        if (upgraded && cardStrings.UPGRADE_DESCRIPTION != null)
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        else
            rawDescription = cardStrings.DESCRIPTION;

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
        if (damageModList.contains(PHANTASMAL))
            rawDescription = rawDescription.replace("!Icon1! ", "!Icon1! " + PHANTASMAL_STRING + " ");
        if (damageModList.contains(ICE))
            rawDescription = rawDescription.replace("!Icon2! ", "!Icon2! " + COLD_STRING + " ");

        rawDescription = rawDescription.replace("!Icon1! ", COLD_STRING +" ");
        rawDescription = rawDescription.replace("!Icon2! ", PHANTASMAL_STRING + " ");

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
    }

    @Override
    public void addModifier(elenum element, boolean tips) {
        AbstractDamageModifier removeMod = null;

        if (element == elenum.ICE) {
            for (AbstractDamageModifier mod : DamageModifierManager.modifiers(this))
                if (mod instanceof FakeIceDamage)
                    removeMod = mod;
        }
        if (removeMod != null) {
            DamageModifierManager.removeModifier(this, removeMod);
            damageModList.remove(FAKE_ICE);
            removeMod = null;
        }

        if (element == elenum.PHANTASMAL) {
            for (AbstractDamageModifier mod : DamageModifierManager.modifiers(this))
                if (mod instanceof FakePhantasmalDamage)
                    removeMod = mod;
        }
        if (removeMod != null) {
            DamageModifierManager.removeModifier(this, removeMod);
            damageModList.remove(FAKE_PHANTASMAL);
            removeMod = null;
        }

        super.addModifier(element, tips);
    }

    @Override
    public void addModifier(ArrayList<elenum> elements, boolean tips) {
        for (elenum e : elements)
            addModifier(e, tips);
    }

    @Override
    public void addModifier(elenum element) {
        super.addModifier(element, true);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}