package theArcanist.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theArcanist.ArcanistMod;
import theArcanist.VFX.MiasmaEffect;
import theArcanist.powers.NauseousPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class StinkingCloud extends AbstractArcanistCard {
    public final static String ID = makeID("StinkingCloud");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 2;
    private final static int COST = 1;

    public StinkingCloud() {
        super(ID, COST, AbstractCard.CardType.SKILL, ArcanistMod.Enums.UNIQUE, AbstractCard.CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        magicOneIsDebuff = true;
        magicTwoIsDebuff = true;
    }

    @Override
    public void onUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        forAllMonstersLiving(mon -> {
            vfx(new MiasmaEffect(mon.hb.cX, mon.hb.cY));
            applyToEnemy(mon, new WeakPower(mon, magicNumber, false));
            applyToEnemy(mon, new NauseousPower(mon, secondMagic));
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}