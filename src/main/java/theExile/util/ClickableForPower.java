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
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theExile.actions.FlashClickyAction;
import theExile.powers.PowerWithButton;

import java.util.ArrayList;

import static theExile.util.Wiz.adp;
import static theExile.util.Wiz.atb;

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
        if (adp().powers.contains(power)) {
            float y = TipHelper.calculateToAvoidOffscreen(powerUI.getHoverTips(), (float)InputHelper.mY);
            TipHelper.queuePowerTips((float)InputHelper.mX + 60.0F * Settings.scale, (float)InputHelper.mY + y,
                    powerUI.getHoverTips());
        }
    }

    protected void onClick() {
        if (adp().powers.contains(power))
            if (!AbstractDungeon.actionManager.turnHasEnded && !AbstractDungeon.isScreenUp
                    && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.actionManager.usingCard
                    && !powerUI.isButtonDisabled() && AbstractDungeon.actionManager.currentAction == null)
                powerUI.onButtonPress();
    }

    protected void onUnhover() {
    }

    public void update() {
        if (!adp().powers.contains(power)) {
            grayscale = true;
            return;
        }
        if (AbstractDungeon.overlayMenu != null && AbstractDungeon.overlayMenu.energyPanel != null) {
            int orbWidth = ReflectionHacks.getPrivate(AbstractDungeon.overlayMenu.energyPanel, EnergyPanel.class,
                    "RAW_W");
            float orbWidthFloat = (float) orbWidth;
            setX(AbstractDungeon.overlayMenu.energyPanel.current_x - (orbWidthFloat * 0.4F + 32.0F) * Settings.xScale);
            grayscale = powerUI.isButtonDisabled();
            super.update();
        }
    }

    public void render(SpriteBatch sb) {
        if (!adp().powers.contains(power))
            return;
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
        int count = -1;
        if (adp().powers.contains(powerUI))
            count = powerUI.getNumber();
        if (count > -1)
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L,
                    Integer.toString(count), hitbox.x - 16*Settings.scale,
                    hitbox.y + hitbox.height/2.0f , Color.WHITE.cpy(), 1.0f);
    }

    public static ArrayList<ClickableForPower> getClickableList() {
        if (clickableList == null)
            clickableList = new ArrayList<>();

        return clickableList;
    }

    public static void updateClickableList() {
        if (clickableList == null)
            clickableList = new ArrayList<>();

        if (adp() == null) {
            clickableList.clear();
            return;
        }

        clickableList.removeIf(clicky -> !adp().powers.contains(clicky.power));

        for (AbstractPower pow : adp().powers) {
            if (pow instanceof PowerWithButton) {
                boolean needNewClicky = true;
                for (ClickableForPower clicky : clickableList) {
                    if (clicky.power == pow) {
                        needNewClicky = false;
                        break;
                    }
                }

                if (needNewClicky) {
                    ClickableForPower newClicky = new ClickableForPower((PowerWithButton) pow,0f,
                            CE_Y + (1 + clickableList.size() + ClickableForRelic.getClickableList().size()) * Y_INCREMENT,
                            CE_W, CE_H);
                    clickableList.add(newClicky);
                    atb(new FlashClickyAction(newClicky));
                }
            }
        }

        alignClickies();
    }

    public static void alignClickies() {
        int order = 0;
        ArrayList<ClickableForRelic> clickList = ClickableForRelic.getClickableList();

        for (ClickableForRelic clicky : clickList) {
            clicky.setY((CE_Y + (float) order * Y_INCREMENT) * Settings.yScale);
            ++order;
        }

        ArrayList<ClickableForPower> clickList2 = getClickableList();

        for (ClickableForPower clicky : clickList2) {
            clicky.setY((CE_Y + (float) order * Y_INCREMENT) * Settings.yScale);
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