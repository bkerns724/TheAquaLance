package theExile.cards;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.actions.HeatRayAction;
import theExile.util.Wiz;
import theExile.vfx.MySmallLaserEffect;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class HeatRay extends AbstractExileCard {
    public final static String ID = makeID(HeatRay.class.getSimpleName());
    private final static int DAMAGE = 15;
    private final static int UPGRADE_DAMAGE = 5;
    private final static int COST = 2;

    public HeatRay() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        atb(new SFXAction("ORB_PLASMA_CHANNEL", 0.5F));
        Wiz.vfx(new MySmallLaserEffect(adp().hb.cX + 30f* Settings.scale, adp().hb.cY + 55f*Settings.scale,
                m.hb.cX, m.hb.cY ), 0.3F);
        DamageInfo info = new DamageInfo(adp(), damage, DamageInfo.DamageType.NORMAL);
        atb(new HeatRayAction(m, info));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}