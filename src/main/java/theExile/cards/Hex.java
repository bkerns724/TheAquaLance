package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class Hex extends AbstractExileCard {
    public final static String ID = makeID(Hex.class.getSimpleName());
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public Hex() {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new JinxPower(m, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}