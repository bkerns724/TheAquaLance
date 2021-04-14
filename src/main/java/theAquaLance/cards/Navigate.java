package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theAquaLance.AquaLanceMod.makeID;

import static theAquaLance.util.Wiz.*;

public class Navigate extends AbstractEasyCard {
    public final static String ID = makeID("Navigate");
    private final static int MAGIC = 0;
    private final static int DISCARD_AMOUNT = 1;

    public Navigate() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        applyToEnemy(m, new StrengthPower(m, -shardCount));
        applyToEnemy(m, new GainStrengthPower(m, shardCount));
        if (upgraded)
            atb(new DiscardAction(adp(), adp(), DISCARD_AMOUNT, false));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}