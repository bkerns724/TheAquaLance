package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.Method;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class RecklessAttack extends AbstractEasyCard {
    public final static String ID = makeID("RecklessAttack");
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int MAGIC = 3;
    private Method calcMethod;

    public RecklessAttack() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;

        try {
            calcMethod = AbstractMonster.class.getDeclaredMethod("calculateDamage", int.class);
        }
        catch (Exception e) {
            return;
        }
        calcMethod.setAccessible(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int temp = m.getIntentBaseDmg();
        try {
            calcMethod.invoke(m, magicNumber);
        }
        catch (Exception e) {
            return;
        }
        DamageInfo enemyDamageInfo = new DamageInfo(m, m.getIntentDmg(), DamageInfo.DamageType.NORMAL);
        atb(new DamageAction(adp(), enemyDamageInfo, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        m.setIntentBaseDmg(temp);
        try {
            calcMethod.invoke(m, m.getIntentBaseDmg());
        }
        catch (Exception e) {
            return;
        }
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int temp = m.getIntentBaseDmg();
        try {
            calcMethod.invoke(m, magicNumber);
        }
        catch (Exception e) {
            return;
        }
        magicNumber = m.getIntentDmg();
        m.setIntentBaseDmg(temp);
        try {
            calcMethod.invoke(m, m.getIntentBaseDmg());
        }
        catch (Exception e) {
            return;
        }
        isMagicNumberModified = magicNumber != baseMagicNumber;
        super.calculateCardDamage(m);
    }

    @Override
    public void applyPowers() {
        magicNumber = baseMagicNumber;
        isMagicNumberModified = false;
        super.applyPowers();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}