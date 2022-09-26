package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;

public class RazorIce extends AbstractExileCard implements StartupCard {
    public final static String ID = makeID(RazorIce.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public RazorIce() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.ICE);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        Wiz.shuffleIn(this, magicNumber);
        return false;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        dmg(m);
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}