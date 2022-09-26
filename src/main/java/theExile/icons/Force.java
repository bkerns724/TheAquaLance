package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class Force extends AbstractCustomIcon {
    private static Force singleton;
    public static final String ID = ExileMod.makeID(Force.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/Force.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

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
