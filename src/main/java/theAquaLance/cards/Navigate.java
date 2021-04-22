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
    private final static int MAGIC = 1;

    public Navigate() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        if (upgraded)
            shardCount += 2;
        applyToEnemy(m, new StrengthPower(m, -shardCount));
        applyToEnemy(m, new GainStrengthPower(m, shardCount));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}