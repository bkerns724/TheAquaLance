package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class Phantasmal extends AbstractCustomIcon {
    private static Phantasmal singleton;
    public static final String ID = ExileMod.makeID(Phantasmal.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/Force.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public Phantasmal() {
        super(ID, TEXTURE);
    }

    public static Phantasmal get()
    {
        if (singleton == null) {
            singleton = new Phantasmal();
        }
        return singleton;
    }
}
