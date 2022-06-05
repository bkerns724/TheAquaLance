package theArcanist.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.actions.ChaosMagicAction;
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.cards.AbstractArcanistCard.elenum.*;
import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public class GenericResonantCard extends AbstractArcanistCard {
    public final static String ID = makeID(GenericResonantCard.class.getSimpleName());
    private final static int COST = 1;

    public GenericResonantCard(int damage, boolean cold, boolean dark, boolean force, boolean fire, int jinx, int chaos,
                               int draw, int energy) {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage;
        this.jinx = jinx;
        this.chaos = chaos;
        this.extraDraw = draw;
        this.extraEnergy = energy;

        resonant = true;

        if (cold)
            addModifier(ICE, false);
        if (dark)
            addModifier(DARK, false);
        if (force)
            addModifier(FORCE, false);
        if (fire)
            addModifier(FIRE, false);

        baseMagicNumber = magicNumber = jinx;
        baseSecondMagic = secondMagic = chaos;

        customizeCardAttributes();
    }

    @Override
    protected void applyAttributes() {
    }

    // Should not be used
    public GenericResonantCard() {
        this(8, false, false, false, false,
                0, 0, 0, 0);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (name.equals(ElectricChannel.LOC_NAME)) {
            dmg(m, AttackEffect.LIGHTNING);
        } else
            dmg(m);
        if (jinx > 0)
            applyToEnemy(m, new JinxPower(m, jinx));
        for (int i = 0; i < chaos; i++)
            atb(new ChaosMagicAction());
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void customizeCardAttributes() {
        baseMagicNumber = magicNumber = jinx;
        baseSecondMagic = secondMagic = chaos;

        StringBuilder sBuilder = new StringBuilder(cardStrings.EXTENDED_DESCRIPTION[0]);
        if (jinx > 0)
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[1]);
        if (chaos > 0) {
            if (chaos == 1)
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[2]);
            else
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[3]);
        }
        if (extraDraw > 0) {
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[4]);
            sBuilder.append(extraDraw);
            if (extraDraw == 1)
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[5]);
            else
                sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[6]);
        }
        if (extraEnergy > 0) {
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[7]);
            sBuilder.append(extraEnergy);
            sBuilder.append(cardStrings.EXTENDED_DESCRIPTION[8]);
        }
        rawDescription = sBuilder.toString();

        int effectCount = 0;
        if (jinx > 0)
            effectCount++;
        if (chaos > 0)
            effectCount++;
        if (extraEnergy > 0)
            effectCount++;
        if (extraDraw > 0)
            effectCount++;

        name = ElectricChannel.LOC_NAME;
        if (damageModList.size() == 1) {
            elenum ele = damageModList.get(0);
            if (ele == ICE)
                name = ChanneledFrost.LOC_NAME;
            else if (ele == elenum.FORCE)
                name = ChanneledVice.LOC_NAME;
            else if (ele == elenum.FIRE)
                name = ChanneledFlame.LOC_NAME;
            else if (ele == elenum.DARK)
                name = ChanneledVoid.LOC_NAME;
        } else if (effectCount == 1 && damageModList.isEmpty()) {
            if (jinx > 0)
                name = ChanneledCurse.LOC_NAME;
            else if (chaos > 0)
                name = ChanneledChaos.LOC_NAME;
            else if (extraDraw > 0)
                name = cardStrings.EXTENDED_DESCRIPTION[10];
            else if (extraEnergy > 0)
                name = cardStrings.EXTENDED_DESCRIPTION[11];
        } else if (effectCount == 0 && damageModList.isEmpty())
            name = ElectricChannel.LOC_NAME;
        else
            name = cardStrings.EXTENDED_DESCRIPTION[9];

        initializeTitle();
        initializeDescription();
    }

    @Override
    protected AbstractGameAction.AttackEffect getDefaultAttackEffect() {
        if (damage > 15)
            return AbstractGameAction.AttackEffect.SLASH_HEAVY;
        else
            return getRandomSlash();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GenericResonantCard(baseDamage, damageModList.contains(ICE),
                damageModList.contains(DARK),
                damageModList.contains(FORCE),
                damageModList.contains(FIRE),
                jinx, chaos, extraDraw, extraEnergy);
    }
}