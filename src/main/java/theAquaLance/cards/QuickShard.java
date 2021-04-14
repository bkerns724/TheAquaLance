package theAquaLance.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.AquaDrawCardAction;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class QuickShard extends AbstractEmbedCard {
    public final static String ID = makeID("QuickShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;

    public QuickShard() {
        super(ID, 1, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public void onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature host) {
        AquaLanceMod.logger.info("Quick Shard Test");
        AquaLanceMod.logger.info(power.ID);
        AquaLanceMod.logger.info(target.name);
        AquaLanceMod.logger.info(host.name);
        if (power instanceof EmbedPower && target == host)
            att(new AquaDrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    public String getEmbedDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0];
    }
}