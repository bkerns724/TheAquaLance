package theArcanist.Icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class Ice extends AbstractCustomIcon {
    private static Ice singleton;
    public static final String ID = ArcanistMod.makeID(Ice.class.getSimpleName());
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Ice.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "]";

    public Ice() {
        super(ID, TEXTURE);
    }

    public static Ice get()
    {
        if (singleton == null) {
            singleton = new Ice();
        }
        return singleton;
    }
}
