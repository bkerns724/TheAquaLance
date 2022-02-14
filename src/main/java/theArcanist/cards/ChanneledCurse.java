package theArcanist.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.VFX.DarkWaveEffect;
import theArcanist.patches.ResonantPowerPatch;
import theArcanist.powers.JinxPower;
import theArcanist.powers.ResonatingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ChanneledCurse extends AbstractArcanistCard {
    public final static String ID = makeID("ChanneledCurse");
    private final static int DAMAGE = 8;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public ChanneledCurse() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        ResonantPowerPatch.AbstractCardField.resonance.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p != null && m != null)
            vfx(new DarkWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F);
        dmg(m, AttackEffect.NONE);
        applyToEnemy(m, new JinxPower(m, magicNumber));
        applyToSelf(new ResonatingPower(p, baseDamage, false, false, false, false, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}