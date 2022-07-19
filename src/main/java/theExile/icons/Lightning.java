package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class Lightning extends AbstractCustomIcon {
    private static Lightning singleton;
    public static final String ID = ExileMod.makeID(Lightning.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/Lightning.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public Lightning() {
        super(ID, TEXTURE);
    }

    public static Lightning get()
    {
        if (singleton == null) {
            singleton = new Lightning();
        }
        return singleton;
    }
}
