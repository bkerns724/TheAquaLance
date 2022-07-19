package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.JinxPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToEnemy;

public class CurseWeapon extends AbstractExileCard {
    public final static String ID = makeID(CurseWeapon.class.getSimpleName());
    private final static int BLOCK = 8;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 3;
    private final static int COST = 1;

    public CurseWeapon() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new JinxPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}