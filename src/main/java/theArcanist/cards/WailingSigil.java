package theArcanist.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import theArcanist.powers.TempNegStrengthPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class WailingSigil extends AbstractSigilCard {
    public final static String ID = makeID("WailingSigil");
    private final static int MAGIC = 4;
    private final static int UPGRADE_MAGIC = 2;

    public WailingSigil() {
        super(ID, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        AbstractPlayer p = AbstractDungeon.player;
        vfx(new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.BLACK, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F);
        forAllMonstersLiving(monster -> applyToEnemy(monster, new TempNegStrengthPower(monster, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}