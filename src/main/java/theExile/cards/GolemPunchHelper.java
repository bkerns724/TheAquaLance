package theExile.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.thornDmg;

@AutoAdd.Ignore
public class GolemPunchHelper extends AbstractExileCard {
    public final static String ID = makeID(GolemPunchHelper.class.getSimpleName());
    private final static int DAMAGE = 30;
    private final static int COST = 0;

    public GolemPunchHelper() {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.FORCE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        thornDmg(m, baseDamage, ExileMod.Enums.FORCE_M);
    }

    public void upp() {
    }
}