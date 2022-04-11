package theArcanist.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import theArcanist.damagemods.ScourgeType;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class Zap extends AbstractArcanistCard {
    public final static String ID = makeID(Zap.class.getSimpleName());
    private final static int DAMAGE = 5;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int COST = 1;

    public Zap() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        DamageModifierManager.addModifier(this, new ScourgeType());
        hasScourge = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        vfx(new LightningEffect(m.drawX, m.drawY));
        dmg(m, AbstractGameAction.AttackEffect.NONE, Color.YELLOW.cpy());
        if (getJinxAmountCard(m) > 0)
            applyToEnemy(m, new WeakPower(m, getJinxAmountCard(m), false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}