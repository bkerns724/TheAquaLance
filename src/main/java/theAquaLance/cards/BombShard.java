package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.EmbedAction;
import theAquaLance.actions.PopOneAction;
import theAquaLance.powers.EmbedPower;
import theAquaLance.powers.IntelligencePower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class BombShard extends AbstractEmbedCard {
    public final static String ID = makeID("BombShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int SECOND_DAMAGE = 20;
    private final static int MAGIC_NUMBER = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 3;
    private int counter = 0;

    public BombShard() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
        magicNumber = baseMagicNumber = MAGIC_NUMBER;
        secondMagic = baseSecondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
        counter = secondMagic;
    }

    @Override
    public void atStartOfTurn(AbstractCreature host) {
        counter--;
        EmbedPower pow = (EmbedPower) host.getPower(EmbedPower.POWER_ID);
        if (counter <= 0) {
            if (pow != null)
                addToBot(new PopOneAction(host, this));
            counter = secondMagic;
        }
        else
            pow.updateDescription();
    }

    @Override
    public void onPop(AbstractCreature host) {
        if (host instanceof AbstractMonster) {
            calculateCardDamage((AbstractMonster) host);
            dmgTwoTop(host, AquaLanceMod.Enums.WATER);
        }
    }

    @Override
    public void applyPowers() {
        AbstractPower intelligence = AbstractDungeon.player.getPower(IntelligencePower.POWER_ID);
        if (intelligence != null)
            intelligence.amount *= magicNumber;

        super.applyPowers();
        if (intelligence != null)
            intelligence.amount /= magicNumber;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower intelligence = AbstractDungeon.player.getPower(IntelligencePower.POWER_ID);
        if (intelligence != null)
            intelligence.amount *= magicNumber;

        super.calculateCardDamage(mo);
        if (intelligence != null)
            intelligence.amount /= magicNumber;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    public String getDesc() {
        String[] DESC = cardStrings.EXTENDED_DESCRIPTION;
        applyPowers();
        if (counter == 1)
            return DESC[0] + counter + DESC[1] + secondDamage + DESC[3];
        else
            return DESC[0] + counter + DESC[2] + secondDamage + DESC[3];
    }
}