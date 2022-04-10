package theArcanist.Icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class Scourge extends AbstractCustomIcon {
    private static Scourge singleton;
    public static final String ID = ArcanistMod.makeID(Scourge.class.getSimpleName());
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Scourge.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public Scourge() {
        super(ID, TEXTURE);
    }

    public static Scourge get()
    {
        if (singleton == null) {
            singleton = new Scourge();
        }
        return singleton;
    }
}
