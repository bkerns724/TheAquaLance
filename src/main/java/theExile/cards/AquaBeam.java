package theExile.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.ExileMod;
import theExile.powers.FrostbitePower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class AquaBeam extends AbstractExileCard {
    public final static String ID = makeID(AquaBeam.class.getSimpleName());
    private final static int COST = -1;

    public AquaBeam() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void applyAttributes() {
        exhaust = true;
    }

    @Override
    public void singleTargetUse(AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                int effect = EnergyPanel.totalCount;
                if (energyOnUse != -1)
                    effect = energyOnUse;

                if (adp().hasRelic("Chemical X")) {
                    effect += 2;
                    adp().getRelic("Chemical X").flash();
                }

                if (upgraded)
                    effect++;

                AbstractPower pow = null;
                if (m != null)
                    pow = m.getPower(FrostbitePower.POWER_ID);

                if (effect > 0) {
                    for(int i = 0; i < effect; ++i)
                        if (pow != null && pow.amount > 0)
                            att(new DamageAction(m, new DamageInfo(adp(), pow.amount, DamageInfo.DamageType.HP_LOSS),
                                    ExileMod.Enums.WATER));

                    if (!freeToPlayOnce)
                        adp().energy.use(EnergyPanel.totalCount);
                }

                isDone = true;
            }
        });
    }

    public void upp() { }
}