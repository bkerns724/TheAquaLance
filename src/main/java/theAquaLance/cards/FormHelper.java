package theAquaLance.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

@AutoAdd.Ignore
public class FormHelper extends AbstractEasyCard {
    public final static String ID = makeID("FormHelper");
    private final static int SECOND_DAMAGE = 1;
    private final static int COST = 0;

    public FormHelper() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTwo(m, AquaLanceMod.Enums.WATER);
    }

    public void upp() {
    }
}