package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class HexingSteam extends AbstractExileCard {
    public final static String ID = makeID(HexingSteam.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public HexingSteam() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        magicTwoIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new JinxPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}