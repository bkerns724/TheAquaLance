package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamSigil extends AbstractEasyCard {
    public final static String ID = makeID("SteamSigil");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;

    public SteamSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.COMMON, CardTarget.NONE);
        baseDamage = DAMAGE;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void onManualDiscard() {
        atb(new DamageRandomEnemyAction(new DamageInfo(adp(), damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}