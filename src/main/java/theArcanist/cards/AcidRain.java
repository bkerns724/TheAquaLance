package theArcanist.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theArcanist.ArcanistMod;
import theArcanist.powers.CorrodedPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class AcidRain extends AbstractArcanistCard {
    public final static String ID = makeID(AcidRain.class.getSimpleName());
    private final static int SECOND_MAGIC = 1;
    private final static int UPGRADE_SECOND = 1;
    private final static int COST = 1;
    private final static int MAGIC = 2;

    public AcidRain() {
        super(ID, COST, CardType.SKILL, ArcanistMod.Enums.UNIQUE, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        isMultiDamage = true;
        magicOneIsDebuff = true;
        magicTwoIsDebuff = true;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> {
            applyToEnemy(mon, new CorrodedPower(mon, magicNumber));
            applyToEnemy(mon, new VulnerablePower(mon, secondMagic, false));
            vfx(new FlashAtkImgEffect(mon.hb.cX, mon.hb.cY, ArcanistMod.Enums.ACID));
        });
    }

    public void upp() {
        upMagic2(UPGRADE_SECOND);
    }
}