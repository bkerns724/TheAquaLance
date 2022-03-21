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
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public class GenericResonantCard extends AbstractArcanistCard {
    public final static String ID = makeID(GenericResonantCard.class.getSimpleName());
    private final static int COST = 1;
    private final boolean cold;
    private final boolean dark;
    private final boolean force;
    private final boolean fire;
    private final int draw;
    private final int energy;
    private AttackEffect attackEffect = AttackEffect.NONE;

    public GenericResonantCard(int damage, boolean cold, boolean dark, boolean force, boolean fire, int jinx, int chaos,
                               int draw, int energy) {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.cold = cold;
        this.dark = dark;
        this.force = force;
        this.fire = fire;
        baseDamage = damage;
        this.jinx = jinx;
        this.chaos = chaos;
        this.draw = draw;
        this.energy = energy;

        resonant = true;

        if (cold)
            DamageModifierManager.addModifier(this, new IceDamage(false));
        if (dark)
            DamageModifierManager.addModifier(this, new DarkDamage(false));
        if (force)
            DamageModifierManager.addModifier(this, new ForceDamage(false));
        if (fire)
            DamageModifierManager.addModifier(this, new SoulFireDamage(false));

        baseMagicNumber = magicNumber = jinx;
        baseSecondMagic = secondMagic = chaos;

        customizeCardAttributes();
    }

    // Should not be used
    public GenericResonantCard() {
        this(8, false, false, false, false,
                0, 0, 0, 0);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (attackEffect == AttackEffect.NONE)
            vfx(new DarkWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F);
        dmg(m, attackEffect);
        if (jinx > 0)
            applyToEnemy(m, new JinxPower(m, jinx));
        for (int i = 0; i < chaos; i++)
            atb(new ChaosMagicAction());
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

        baseMagicNumber = magicNumber = jinx;
        baseSecondMagic = secondMagic = chaos;

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
        if (jinx > 0)
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[2]);
        if (chaos > 0) {
            if (chaos == 1)
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[3]);
            else
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[4]);
        }
        if (draw > 0) {
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[5]);
            sBuilder.append(draw);
            if (draw == 1)
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[6]);
            else
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[7]);
        }
        if (energy > 0) {
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[8]);
            sBuilder.append(energy);
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[9]);
        }
        sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[10]);
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
        else if (count == 0) {
            attackEffect = AttackEffect.BLUNT_LIGHT;
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
        return new GenericResonantCard(baseDamage, cold, dark, force, fire, jinx, chaos,
                draw, energy);
    }
}