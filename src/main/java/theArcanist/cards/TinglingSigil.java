package theArcanist.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class TinglingSigil extends AbstractArcanistCard {
    public final static String ID = makeID(TinglingSigil.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;

    public TinglingSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseDamage = DAMAGE;
        isMultiDamage = true;
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ORB_LIGHTNING_EVOKE"));
        forAllMonstersLiving(monster ->
                atb(new VFXAction(new LightningEffect(monster.drawX, monster.drawY), 0.02f)));
        allDmg(AbstractGameAction.AttackEffect.NONE, Color.YELLOW);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}