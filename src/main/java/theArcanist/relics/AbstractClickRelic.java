package theArcanist.relics;

import basemod.AutoAdd;
import basemod.ClickableUIElement;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.github.tommyettinger.colorful.Shaders;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theArcanist.ArcanistMod;
import theArcanist.vfx.FlashClickRelicEffect;
import theArcanist.util.TexLoader;

import java.lang.reflect.Type;

import static theArcanist.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractClickRelic extends AbstractArcanistRelic implements CustomSavable<Boolean> {
    private RelicClickable element;

    // Constructor of ClickableUIElement scales its inputs with Settings.scale
    private static final float CE_X = 64.0F;
    private static final float CE_Y = 132.0F*Settings.yScale/Settings.scale;
    private static final float CE_W = 64f;
    private static final float CE_H = 48f;
    private static final float Y_INCREMENT = 56f;
    private int order = 0;
    protected static final ShaderProgram shade = new ShaderProgram(Shaders.vertexShaderHSLC, Shaders.fragmentShaderHSLC);
    protected static final Color HSLC = new Color(0.5f, 0.5f, 0.7f, 0.5f);

    protected boolean firstBattle = true;

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
    }

    @Override
    public void atTurnStartPostDraw() {
        if (firstBattle && !grayscale)
            element.firstBattleFlash();
    }

    @Override
    public void update() {
        super.update();
        element.update();
    }

    @Override
    public Boolean onSave() {
        return firstBattle;
    }

    @Override
    public void onLoad(Boolean foo) {
        if (foo != null)
            firstBattle = foo;
        else
            ArcanistMod.logger.info("Loaded a null value");
    }

    @Override
    public Type savedType()
    {
        return new TypeToken<Boolean>(){}.getType();
    }

    public RelicClickable getElement() {return element;}

    public abstract void buttonPress();

    public class RelicClickable extends ClickableUIElement {
        protected boolean elementGrayscale = false;

        public RelicClickable(String textureString) {
            super(TexLoader.getTexture(textureString), CE_X, CE_Y + order * Y_INCREMENT, CE_W, CE_H);
        }

        @Override
        protected void onHover() {
            if (firstBattle)
                return;
            float y = TipHelper.calculateToAvoidOffscreen(tips, InputHelper.mY);
            TipHelper.queuePowerTips((float)InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY + y, tips);
        }

        @Override
        protected void onClick() {
            if (!AbstractDungeon.actionManager.turnHasEnded && !AbstractDungeon.isScreenUp &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    !AbstractDungeon.actionManager.usingCard && !grayscale) {
                buttonPress();
            }
        }

        public void firstBattleFlash() {
            AbstractGameEffect effect = new FlashClickRelicEffect(AbstractClickRelic.this);
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    if (duration == startDuration) {
                        isDone = true;
                        firstBattle = false;
                    }
                }
            });
            atb(new VFXAction(effect, 0.5f));
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

    public void doRender(SpriteBatch sb) {
        if (firstBattle)
            return;
        if (grayscale || element.elementGrayscale)
            element.render(sb, Color.GRAY.cpy());
        else {
            Hitbox hitbox = ReflectionHacks.getPrivate(element, ClickableUIElement.class, "hitbox");
            if (!hitbox.hovered)
                getElement().render(sb, Color.WHITE.cpy());
            else {
                ShaderProgram oldShade = sb.getShader();
                sb.setShader(shade);
                element.render(sb, HSLC);
                sb.setShader(oldShade);
                sb.setColor(Color.WHITE.cpy());
            }
        }
    }
}
