package theAquaLance.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.cards.AbstractEasyCard;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class RecklessThrust extends AbstractEasyCard {
    public final static String ID = makeID("RecklessThrust");
    private final static int DAMAGE = 1;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;

    public RecklessThrust() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        AutoplayField.autoplay.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageRandomEnemyAction(new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}