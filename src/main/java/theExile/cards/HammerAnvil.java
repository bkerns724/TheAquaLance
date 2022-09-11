package theExile.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
import theExile.actions.HammerAnvilAction;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

public class HammerAnvil extends AbstractExileCard {
    public final static String ID = makeID(HammerAnvil.class.getSimpleName());
    private final static int DAMAGE = 4;
    private final static int UPGRADE_DAMAGE = 1;
    private final static int MAGIC = 5;
    private final static int COST = 1;

    public HammerAnvil() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        addModifier(elenum.FORCE);
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        DamageModContainer container = new DamageModContainer(this, DamageModifierManager.modifiers(this));
        DamageInfo info = BindingHelper.makeInfo(container, adp(), damage, DamageInfo.DamageType.NORMAL);
        Wiz.vfx(new ViceCrushEffect(m.hb.cX, m.hb.cY), 0.5F);
        atb(new HammerAnvilAction(m, magicNumber, info));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}