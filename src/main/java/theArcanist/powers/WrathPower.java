package theArcanist.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theArcanist.vfx.WrathParticleEffect2;
import theArcanist.vfx.WrathStanceEffectMonster;

import static theArcanist.ArcanistMod.makeID;

public class WrathPower extends AbstractArcanistPower
        implements CloneablePowerInterface, InvisiblePower {
    public AbstractCreature source;
    public float particleTimer;
    public float particleTimer2;

    public static final String POWER_ID = makeID(WrathPower.class.getSimpleName());

    public WrathPower(AbstractCreature owner) {
        super(POWER_ID, PowerType.BUFF, false, owner, -1);
        ID = POWER_ID;

        updateDescription();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
    }

    @Override
    public void updateParticles() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                float thisX = this.owner.hb.cX + MathUtils.random(-this.owner.hb.width / 2.0F - 30.0F * Settings.scale, this.owner.hb.width / 2.0F + 30.0F * Settings.scale);
                float thisY = this.owner.hb.cY + MathUtils.random(-this.owner.hb.height / 2.0F - -10.0F * Settings.scale, this.owner.hb.height / 2.0F - 10.0F * Settings.scale);
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect2(thisX, thisY));
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new WrathStanceEffectMonster("Wrath", this.owner));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new WrathPower(owner);
    }
}