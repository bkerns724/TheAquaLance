package theArcanist.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class Dark extends AbstractCustomIcon {
    private static Dark singleton;
    public static final String ID = ArcanistMod.makeID(Dark.class.getSimpleName());
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Dark.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public Dark() {
        super(ID, TEXTURE);
    }

    public static Dark get()
    {
        if (singleton == null) {
            singleton = new Dark();
        }
        return singleton;
    }
}
