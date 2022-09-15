package theExile.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theExile.util.Wiz;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.adRoom;
import static theExile.util.Wiz.atb;

public class BellTollsPower extends AbstractExilePower {
    public static String POWER_ID = makeID(BellTollsPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BellTollsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF, false, owner, amount);
        this.name = NAME;
        priority = 4;
    }

    @Override
    public void atEndOfRound() {
        if (adRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            int count = Wiz.getDebuffCount(owner) * amount;
            if (count > 0) {
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        flashWithoutSound();
                        CardCrawlGame.sound.playAV("BELL", -0.25f, 0.75f);
                        Wiz.applyToEnemyTop(owner, new RingingPower(owner, count));
                        isDone = true;
                    }
                });
            }
        }
    }
}