package theExile.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.ExileMod;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.forAllMonstersLiving;
import static theExile.util.Wiz.thornDmg;

@AutoAdd.Ignore
public class GolemFrostHelper extends AbstractExileCard {
    public final static String ID = makeID(GolemFrostHelper.class.getSimpleName());
    private final static int DAMAGE = 20;
    private final static int COST = 0;

    public GolemFrostHelper() {
        super(ID, COST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        addModifier(elenum.ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> thornDmg(mon, baseDamage, ExileMod.Enums.ICE_M));
    }

    public void upp() {
    }
}