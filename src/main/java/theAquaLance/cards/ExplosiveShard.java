package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.EmbedPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class ExplosiveShard extends AbstractEmbedCard {
    public final static String ID = makeID("ExplosiveShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 20;
    private final static int UPGRADE_MAGIC = 8;
    private int counter = 0;

    public ExplosiveShard() {
        super(ID, 1, CardRarity.COMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        counter = 3;
        atb(new EmbedAction(this, m));
    }

    @Override
    public void onPopped(AbstractCreature host) {
        atb(new DamageAction(host, new DamageInfo(adp(), magicNumber), AbstractGameAction.AttackEffect.FIRE));
    }

    public void atStartOfTurn(EmbedPower pow) {
        counter--;
        if (counter == 0)
            pow.popCard(this);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}