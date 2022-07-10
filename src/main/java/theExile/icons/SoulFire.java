package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class SoulFire extends AbstractCustomIcon {
    private static SoulFire singleton;
    public static final String ID = ExileMod.makeID(SoulFire.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/SoulFire.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public SoulFire() {
        super(ID, TEXTURE);
    }

    public static SoulFire get()
    {
        if (singleton == null)
            singleton = new SoulFire();
        return singleton;
    }
}
