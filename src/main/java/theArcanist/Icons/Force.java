package theArcanist.Icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class Force extends AbstractCustomIcon {
    private static Force singleton;
    public static final String ID = ArcanistMod.makeID(Force.class.getSimpleName());
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Force.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "]";

    public Force() {
        super(ID, TEXTURE);
    }

    public static Force get()
    {
        if (singleton == null) {
            singleton = new Force();
        }
        return singleton;
    }
}
