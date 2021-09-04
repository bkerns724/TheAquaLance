package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.ShardXAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ChargedShard extends AbstractEmbedCard {
    public final static String ID = makeID("ChargedShard");
    private final static int MAGIC = 12;
    private final static int UPGRADE_MAGIC = -2;
    private final static int SECOND_DAMAGE = 10;
    private final static int COST = -1;
    private final static int ENERGY_MULT = 2;
    private int counter;
    public int attuned;

    public ChargedShard() {
        super(ID, COST, CardRarity.UNCOMMON);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        counter = magicNumber;
        atb(new ShardXAction(freeToPlayOnce, energyOnUse, this, ENERGY_MULT));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }

    @Override
    public void onCardDraw(AbstractCreature host) {
        counter--;
        if (counter <= 0 && host instanceof AbstractMonster) {
            counter = magicNumber;
            calculateCardDamage((AbstractMonster) host);
            dmgTwoTop(host, AquaLanceMod.Enums.WATER);
        }
    }

    @Override
    public void applyPowers() {
        AbstractPower intelligence = AbstractDungeon.player.getPower("intelligence");
        if (intelligence != null)
            intelligence.amount *= attuned;

        super.applyPowers();
        if (intelligence != null)
            intelligence.amount /= attuned;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower intelligence = AbstractDungeon.player.getPower("intelligence");
        if (intelligence != null)
            intelligence.amount *= attuned;

        super.calculateCardDamage(mo);
        if (intelligence != null)
            intelligence.amount /= attuned;
    }

    @Override
    public String getDesc() {
        String[] DESC = cardStrings.EXTENDED_DESCRIPTION;
        return DESC[0] + secondDamage + DESC[1] + magicNumber + DESC[2];
    }
}