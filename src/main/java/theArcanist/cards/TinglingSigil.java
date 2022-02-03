package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class TinglingSigil extends AbstractSigilCard {
    public final static String ID = makeID("TinglingSigil");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;

    public TinglingSigil() {
        super(ID, CardRarity.COMMON, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    @Override
    public void onManualDiscard() {
        allDmg(AbstractGameAction.AttackEffect.NONE);

        forAllMonstersLiving(monster ->
                att(new VFXAction(new LightningEffect(monster.drawX, monster.drawY), 0.0f)));
        att(new SFXAction("ORB_LIGHTNING_EVOKE"));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}