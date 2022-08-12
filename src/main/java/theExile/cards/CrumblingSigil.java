package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.CrumblingPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;
import static theExile.util.Wiz.forAllMonstersLiving;

public class CrumblingSigil extends AbstractExileCard {
    public final static String ID = makeID(CrumblingSigil.class.getSimpleName());
    private final static int MAGIC = 50;
    private final static int COST = -2;

    public CrumblingSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
        sigil = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> applyToEnemy(mon, new CrumblingPower(mon, magicNumber)));
    }

    public void upp() {
        exhaust = false;
    }
}