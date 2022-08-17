package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class Grease extends AbstractExileCard {
    public final static String ID = makeID(Grease.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 0;

    public Grease() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> applyToEnemy(mon, new VulnerablePower(mon, magicNumber, false)));
        discard(1);
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}