package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ShatterShard extends AbstractEmbedCard {
    public final static String ID = makeID("ShatterShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 3;
    private final static int SECOND_DAMAGE = 50;
    private final static int UPGRADE_SECOND = 10;
    private final static int COST = 1;
    private int counter = 0;

    public ShatterShard() {
        super(ID, COST, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
        counter = magicNumber;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_SECOND);
    }

    @Override
    public void onDiscardSigil(AbstractCreature host) {
        counter--;
        if (counter == 0) {
            counter = magicNumber;
            dmgTwoTop(host, AquaLanceMod.Enums.BLOOD);
        }
    }

    @Override
    public void atTurnStart() {
        counter = magicNumber;
    }

    @Override
    public String getDesc() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1] + secondDamage +
                cardStrings.EXTENDED_DESCRIPTION[2];
    }
}