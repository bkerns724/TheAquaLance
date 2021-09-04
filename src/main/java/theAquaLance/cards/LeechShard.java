package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class LeechShard extends AbstractEmbedCard {
    public final static String ID = makeID("LeechShard");
    private final static int DAMAGE = 2;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public LeechShard() {
        super(ID, COST, CardRarity.COMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    @Override
    public void onAttacked(AbstractCreature attacker, AbstractCreature host, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && attacker == adp())
            att(new GainBlockAction(adp(), magicNumber, true));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public String getDesc() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + magicNumber + cardStrings.EXTENDED_DESCRIPTION[1];
    }
}