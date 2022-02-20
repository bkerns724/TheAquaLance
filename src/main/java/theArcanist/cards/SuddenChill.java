package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.IceDamage;
import theArcanist.powers.FrostbitePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class SuddenChill extends AbstractArcanistCard {
    public final static String ID = makeID("SuddenChill");
    private final static int DAMAGE = 6;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public SuddenChill() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        DamageModifierManager.addModifier(this, new IceDamage());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.ICE);
        applyToEnemy(m, new FrostbitePower(m, getJinxAmount(m)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}