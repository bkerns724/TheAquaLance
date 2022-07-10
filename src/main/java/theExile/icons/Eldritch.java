package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class Eldritch extends AbstractCustomIcon {
    private static Eldritch singleton;
    public static final String ID = ExileMod.makeID(Eldritch.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/Dark.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public Eldritch() {
        super(ID, TEXTURE);
    }

    public static Eldritch get()
    {
        if (singleton == null) {
            singleton = new Eldritch();
        }
        return singleton;
    }
}
