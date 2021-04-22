package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theAquaLance.actions.FinisherAction;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamBlast extends AbstractEasyCard {
    public final static String ID = makeID("SteamBlast");
    private final static int DAMAGE = 7;
    private final static int UPGRADE_DAMAGE = 2;

    public SteamBlast() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int shardCount = getShardCount(m);
        if (shardCount == 0)
            return;

        for (int i = 0; i < shardCount; i++)
            dmg(m, AbstractGameAction.AttackEffect.NONE);

        if (shardCount < 5)
            atb(new VFXAction(new FlashAtkImgEffect(m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.FIRE), 0.1F));
        else
            atb(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));

        atb(new FinisherAction(m));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}