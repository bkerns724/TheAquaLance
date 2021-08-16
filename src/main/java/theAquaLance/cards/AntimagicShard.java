package theAquaLance.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Transient;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.AquaLanceMod;
import theAquaLance.actions.EmbedAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class AntimagicShard extends AbstractEmbedCard {
    public final static String ID = makeID("AntimagicShard");
    private final static int DAMAGE = 3;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int SECOND_DAMAGE = 5;
    private final static int UPGRADE_SECOND = 2;
    private final static int COST = 1;

    public AntimagicShard() {
        super(ID, COST, CardRarity.COMMON);
        baseDamage = DAMAGE;
        baseSecondDamage = SECOND_DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_SECOND);
    }

    @Override
    public void onApplyPower(AbstractCreature host, AbstractPower power, AbstractCreature source,
                             AbstractCreature target) {
        if (source == host && !(target == source && power.type == AbstractPower.PowerType.DEBUFF))
            dmgTwo(host, AquaLanceMod.Enums.WATER);
    }

    @Override
    public void onApplyStatus(AbstractCreature host, AbstractCard card) {
        if (card.type == CardType.STATUS)
            dmgTwo(host, AquaLanceMod.Enums.WATER);
    }

    @Override
    public String getDesc() {
        return cardStrings.EXTENDED_DESCRIPTION[0] + secondDamage + cardStrings.EXTENDED_DESCRIPTION[1];
    }
}