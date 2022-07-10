package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class Ice extends AbstractCustomIcon {
    private static Ice singleton;
    public static final String ID = ExileMod.makeID(Ice.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/Ice.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

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
