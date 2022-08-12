package theExile.util;

import basemod.ClickableUIElement;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableForRelic;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.powers.PowerWithButton;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;

public class ClickableForPower extends ClickableUIElement {
    public static final float CE_Y = 132.0F;
    public static final float CE_W = 64.0F;
    public static final float CE_H = 48.0F;
    public static final float Y_INCREMENT = 56.0F;
    private AbstractPower power;
    private final PowerWithButton powerUI;
    private static ArrayList<ClickableForPower> clickableList;
    private boolean grayscale;
    private static final ShaderProgram shade;

    public ClickableForPower(PowerWithButton powerUI, float x, float y, float width, float height) {
        super(powerUI.getTexture(), x, y, width, height);
        this.powerUI = powerUI;
        if (powerUI instanceof AbstractPower)
            this.power = (AbstractPower)powerUI;

        grayscale = powerUI.isButtonDisabled();
    }

    protected void onHover() {
        if (power != null) {
            float y = TipHelper.calculateToAvoidOffscreen(powerUI.getHoverTips(), (float)InputHelper.mY);
            TipHelper.queuePowerTips((float)InputHelper.mX + 60.0F * Settings.scale, (float)InputHelper.mY + y,
                    powerUI.getHoverTips());
        }
    }

    protected void onClick() {
        if (power != null)
            if (!AbstractDungeon.actionManager.turnHasEnded && !AbstractDungeon.isScreenUp
                    && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.actionManager.usingCard
                    && !powerUI.isButtonDisabled())
                powerUI.onButtonPress();
    }

    protected void onUnhover() {
    }

    public void update() {
        if (AbstractDungeon.overlayMenu != null && AbstractDungeon.overlayMenu.energyPanel != null && power != null) {
            int orbWidth = ReflectionHacks.getPrivate(AbstractDungeon.overlayMenu.energyPanel, EnergyPanel.class,
                    "RAW_W");
            float orbWidthFloat = (float) orbWidth;
            setX(AbstractDungeon.overlayMenu.energyPanel.current_x - (orbWidthFloat * 0.4F + 32.0F) * Settings.xScale);
            grayscale = powerUI.isButtonDisabled();
            super.update();
        } else if (power == null)
            updateClickableList();
    }

    public void render(SpriteBatch sb) {
        if (!adp().powers.contains(power))
            grayscale = true;
        if (grayscale)
            super.render(sb, Color.GRAY.cpy());
        else if (!hitbox.hovered)
            render(sb, Color.WHITE.cpy());
        else {
            ShaderProgram oldShade = sb.getShader();
            sb.setShader(shade);
            super.render(sb, Color.WHITE.cpy());
            sb.setShader(oldShade);
        }
    }

    public static ArrayList<ClickableForPower> getClickableList() {
        if (clickableList == null)
            clickableList = new ArrayList<>();

        return clickableList;
    }

    public static void updateClickableList() {
        if (clickableList == null)
            clickableList = new ArrayList<>();

        clickableList.removeIf(clicky -> clicky.getPower() == null);

        if (adp() != null)
            clickableList.removeIf(clicky -> !adp().powers.contains(clicky.power));
        else
            clickableList.clear();

        if (adp() == null)
            return;

        for (AbstractPower pow : adp().powers) {
            if (pow instanceof PowerWithButton) {
                for (ClickableForPower clicky : clickableList) {
                    if (clicky.power == pow)
                        break;
                }

                ClickableForPower newClicky = new ClickableForPower((PowerWithButton) pow,
                        0f, ClickableForRelic.CE_Y + (1 + clickableList.size()) * Y_INCREMENT,
                        ClickableForRelic.CE_W, ClickableForRelic.CE_H);
                clickableList.add(newClicky);
            }
        }

        AlignClickies();
    }

    private static void AlignClickies() {
        int order = 0;
        ClickableForRelic.updateClickableList();
        ArrayList<ClickableForRelic> clickList = ClickableForRelic.getClickableList();

        for (ClickableForRelic clicky : clickList) {
            clicky.setY((132.0F + (float) order * 56.0F) * Settings.yScale);
            ++order;
        }

        ClickableForPower.updateClickableList();
        ArrayList<ClickableForPower> clickList2 = ClickableForPower.getClickableList();

        for (ClickableForPower clicky : clickList2) {
            clicky.setY((132.0F + (float) order * 56.0F) * Settings.yScale);
            ++order;
        }
    }

    public AbstractPower getPower() {
        return power;
    }

    static {
        shade = new ShaderProgram(Gdx.files.internal("shaders/stslib/light.vs"), Gdx.files.internal("shaders/stslib/light.fs"));
    }
}