package theArcanist.cards.damageMods;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class ScourgeIcon extends AbstractCustomIcon {
    private static ScourgeIcon singleton;
    public static final String ID = ArcanistMod.makeID("Scourge");
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Scourge.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    private ScourgeIcon() {
        super(ID, TEXTURE);
    }

    public static ScourgeIcon get()
    {
        if (singleton == null) {
            singleton = new ScourgeIcon();
        }
        return singleton;
    }
}
