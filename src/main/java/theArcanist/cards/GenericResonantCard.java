package theArcanist.cards;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.VFX.DarkWaveEffect;
import theArcanist.actions.ChaosMagicAction;
import theArcanist.cards.damageMods.*;
import theArcanist.patches.ResonantPowerPatch;
import theArcanist.powers.JinxPower;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public class GenericResonantCard extends AbstractArcanistCard {
    public final static String ID = makeID("GenericResonantCard");
    private final static int COST = 1;
    private boolean cold;
    private boolean dark;
    private boolean force;
    private boolean fire;
    private AttackEffect attackEffect = AttackEffect.NONE;

    public GenericResonantCard(int damage, boolean cold, boolean dark, boolean force, boolean fire, int jinx, int chaos) {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.cold = cold;
        this.dark = dark;
        this.force = force;
        this.fire = fire;
        baseDamage = damage;
        magicNumber = baseMagicNumber = jinx;
        secondMagic = baseSecondMagic = chaos;

        ResonantPowerPatch.AbstractCardField.resonance.set(this, true);

        if (cold)
            DamageModifierManager.addModifier(this, new IceDamage(false));
        if (dark)
            DamageModifierManager.addModifier(this, new DarkDamage(false));
        if (force)
            DamageModifierManager.addModifier(this, new ForceDamage(false));
        if (fire)
            DamageModifierManager.addModifier(this, new SoulFireDamage(false));

        customizeCardAttributes();
    }

    public GenericResonantCard() {
        this(8, false, false, false, false, 0, 0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (attackEffect == AttackEffect.NONE)
            vfx(new DarkWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F);
        dmg(m, attackEffect);
        if (magicNumber > 0)
            applyToEnemy(m, new JinxPower(m, magicNumber));
        for (int i = 0; i < secondMagic; i++)
            atb(new ChaosMagicAction());
        applyToSelf(new ResonatingPower(p, baseDamage, cold, dark, force, fire, magicNumber, secondMagic));
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void customizeCardAttributes() {
        int count = 0;
        if (cold) count++;
        if (fire) count++;
        if (force) count++;
        if (dark) count++;
        if (magicNumber > 0) count++;

        StringBuilder sBuilder = new StringBuilder(cardStrings.EXTENDED_DESCRIPTION[0]);
        if (cold)
            sBuilder.append(IceIcon.CODE).append(" ");
        if (force)
            sBuilder.append(ForceIcon.CODE).append(" ");
        if (fire)
            sBuilder.append(SoulFireIcon.CODE).append(" ");
        if (dark)
            sBuilder.append(DarkIcon.CODE).append(" ");
        for (int i = 0; i < count; i++)
            sBuilder.append("  ");
        if (count >= 2) sBuilder.append("NL ");
        sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[1]);
        if (magicNumber > 0)
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[2]);
        sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[3]);
        rawDescription = sBuilder.toString();

        if (count == 1) {
            if (cold) {
                attackEffect = ArcanistMod.Enums.ICE;
                name = "Channeled Frost";
            }
            else if (force) {
                attackEffect = ArcanistMod.Enums.FIST;
                name = "Channeled Vice";
            }
            else if (fire) {
                attackEffect = ArcanistMod.Enums.SOUL_FIRE;
                name = "Channeled Flame";
            }
            else if (dark){
                attackEffect = ArcanistMod.Enums.DARK_COIL;
                name = "Channeled Void";
            }
            else {
                attackEffect = AttackEffect.NONE;
            }
        }
        // Should not happen
        else if (count == 0) {
            attackEffect = AttackEffect.BLUNT_HEAVY;
            name = "Basic Channel";
        }
        else {
            attackEffect = ArcanistMod.Enums.BLOOD;
            name = "Blended Channel";
        }

        initializeTitle();
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GenericResonantCard(baseDamage, cold, dark, force, fire, magicNumber, secondMagic);
    }
}