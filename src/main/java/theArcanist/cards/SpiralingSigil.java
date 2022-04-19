package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.powers.JinxPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class SpiralingSigil extends AbstractArcanistCard {
    public final static String ID = makeID(SpiralingSigil.class.getSimpleName());

    public SpiralingSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        exhaust = true;
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> {
            if (getDebuffCount(mon) > 0)
                applyToEnemy(mon, new JinxPower(mon, getDebuffCount(mon)));
        });
    }

    public void upp() {
        exhaust = false;
    }
}