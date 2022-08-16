package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class DemonFire extends AbstractCustomIcon {
    private static DemonFire singleton;
    public static final String ID = ExileMod.makeID(DemonFire.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/DemonFire.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public DemonFire() {
        super(ID, TEXTURE);
    }

    public static DemonFire get()
    {
        if (singleton == null)
            singleton = new DemonFire();
        return singleton;
    }
}
