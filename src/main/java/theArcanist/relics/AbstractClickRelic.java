package theArcanist.relics;

import basemod.ClickableUIElement;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

import static theArcanist.util.Wiz.*;

public abstract class AbstractClickRelic extends AbstractArcanistRelic {
    private RelicClickable element;

    // Constructor of ClickableUIElement scales its inputs with Settings.scale
    private static final float CE_X = 64.0F;
    private static final float CE_Y = 132.0F*Settings.yScale/Settings.scale;
    private static final float CE_W = 64f;
    private static final float CE_H = 48f;
    private static final float Y_INCREMENT = 56f;
    private int order = 0;

    public AbstractClickRelic(String ID, RelicTier tier, LandingSound sound, AbstractCard.CardColor color,
                              String textureString) {
        super(ID, tier, sound, color);
        if (adp() != null) {
            for (AbstractRelic relic : adp().relics) {
                if (relic instanceof AbstractClickRelic)
                    order++;
            }
        }
        element = new RelicClickable(textureString);
        ArcanistMod.logger.info(this.getClass().getName());
    }

    public RelicClickable getElement() {return element;}

    public abstract void buttonPress();

    public class RelicClickable extends ClickableUIElement {
        public RelicClickable(String textureString) {
            super(TexLoader.getTexture(textureString), CE_X, CE_Y + order * Y_INCREMENT, CE_W, CE_H);
        }

        @Override
        protected void onHover() {
        }

        @Override
        protected void onClick() {
            if (!AbstractDungeon.actionManager.turnHasEnded && !AbstractDungeon.isScreenUp &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    !AbstractDungeon.actionManager.usingCard && !grayscale) {
                // I think this String is hardcoded in SoundMaster and there isn't some static final variable somewhere
                // for me to use.  So I guess I hardcode it too?
                buttonPress();
            }
        }

        @Override
        protected void onUnhover() {
        }

        // Because of course it can't be consistent, setX of ClickableUIElement does NOT scale its input
        // with Settings.scale
        @Override
        public void update() {
            if (AbstractDungeon.overlayMenu == null || AbstractDungeon.overlayMenu.energyPanel == null)
                return;
            float deltaX = AbstractDungeon.overlayMenu.energyPanel.show_x -
                    AbstractDungeon.overlayMenu.energyPanel.current_x;
            setX(CE_X*Settings.scale - deltaX);
            super.update();
        }
    }
}
