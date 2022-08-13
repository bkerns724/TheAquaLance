package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.powers.CrushedPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CurseArmor extends AbstractExileCard {
    public final static String ID = makeID(CurseArmor.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int COST = 1;
    private final static int SECOND_MAGIC = 2;
    private final static int UPGRADE_SECOND = 2;

    public CurseArmor() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new CrushedPower(m, secondMagic));
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}