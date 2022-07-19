package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class Hex extends AbstractExileCard {
    public final static String ID = makeID(Hex.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 0;

    public Hex() {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded)
            applyToEnemy(m, new JinxPower(m, magicNumber));
        else
            forAllMonstersLiving(mon -> applyToEnemy(mon, new JinxPower(mon, magicNumber)));
    }

    public void upp() {
        target = CardTarget.ALL_ENEMY;
    }
}