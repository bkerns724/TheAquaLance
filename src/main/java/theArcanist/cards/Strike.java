package theArcanist.cards;

import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.Icons.Dark;
import theArcanist.Icons.Force;
import theArcanist.Icons.Ice;
import theArcanist.Icons.SoulFire;
import theArcanist.damageMods.*;
import theArcanist.patches.DamageModsIDPatch;
import theArcanist.relics.EnchantmentOils;

import static theArcanist.ArcanistMod.makeID;

public class Strike extends AbstractArcanistCard implements CustomSavable<String> {
    public final static String ID = makeID(Strike.class.getSimpleName());
    public final static int DAMAGE = 6;
    public final static int UPGRADE_DAMAGE = 3;

    public AbstractDamageModifier modifier = null;
    public String modifierID = null;

    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
        if (modifier != null) {
            baseDamage += EnchantmentOils.DAMAGE_BONUS;
            DamageModifierManager.addModifier(this, modifier);
            modifierID = DamageModsIDPatch.ID.get(modifier);
        }
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, getAttackEffect());
    }

    @Override
    public void additionalParsing() {
        String iconString = "";
        String titleString = "";
        if (modifierID != null) {
            if (modifierID.equals(DarkDamage.ID)) {
                iconString = "[" + makeID(Dark.class.getSimpleName()) + "] ";
                titleString = "Dark ";
            }
            else if (modifierID.equals(IceDamage.ID)) {
                iconString = "[" + makeID(Ice.class.getSimpleName()) + "] ";
                titleString = "Ice ";
            }
            else if (modifierID.equals(SoulFireDamage.ID)) {
                iconString = "[" + makeID(SoulFire.class.getSimpleName()) + "] ";
                titleString = "Soul ";
            }
            else if (modifierID.equals(ForceDamage.ID)) {
                iconString = "[" + makeID(Force.class.getSimpleName()) + "] ";
                titleString = "Force ";
            }
        }
        rawDescription = cardStrings.DESCRIPTION.replace("~ICON~", iconString);
        name = cardStrings.NAME.replace("~PREFIX~", titleString);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    private AbstractGameAction.AttackEffect getAttackEffect() {
        if (modifierID != null) {
            if (modifierID.equals(DarkDamage.ID))
                return ArcanistMod.Enums.DARK_COIL;
            else if (modifierID.equals(IceDamage.ID))
                return ArcanistMod.Enums.ICE;
            else if (modifierID.equals(SoulFireDamage.ID))
                return ArcanistMod.Enums.SOUL_FIRE;
            else if (modifierID.equals(ForceDamage.ID))
                return ArcanistMod.Enums.FIST;
        }
        return AbstractGameAction.AttackEffect.BLUNT_LIGHT;
    }

    @Override
    public String onSave() {
        return modifierID;
    }

    @Override
    public void onLoad(String modifierID) {
        this.modifierID = modifierID;
        if (modifierID == null) {
            modifier = null;
            initializeDescription();
            return;
        }
        if (modifierID.equals(DarkDamage.ID))
            modifier = new DarkDamage();
        else if (modifierID.equals(IceDamage.ID))
            modifier = new IceDamage();
        else if (modifierID.equals(SoulFireDamage.ID))
            modifier = new SoulFireDamage();
        else if (modifierID.equals(ForceDamage.ID))
            modifier = new ForceDamage();
        else {
            modifier = null;
            this.modifierID = null;
        }
        if (modifier != null)
            baseDamage += EnchantmentOils.DAMAGE_BONUS;
        initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        Strike card = (Strike) super.makeStatEquivalentCopy();
        card.modifier = modifier;
        card.modifierID = modifierID;
        card.initializeDescription();
        return card;
    }
}