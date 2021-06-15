package theAquaLance.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

@AutoAdd.Ignore
public class EleHelper extends AbstractEasyCard {
    public final static String ID = makeID("EleHelper");
    private final static int SECOND_DAMAGE = 2;

    public EleHelper() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseSecondDamage = SECOND_DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> dmgTwo(mon, AquaLanceMod.Enums.WATER));
    }

    public void upp() {
    }
}