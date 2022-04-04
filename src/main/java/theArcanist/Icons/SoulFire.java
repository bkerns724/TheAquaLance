package theArcanist.Icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class SoulFire extends AbstractCustomIcon {
    private static SoulFire singleton;
    public static final String ID = ArcanistMod.makeID(SoulFire.class.getSimpleName());
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/SoulFire.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "]";

    private SoulFire() {
        super(ID, TEXTURE);
    }

    public static SoulFire get()
    {
        if (singleton == null)
            singleton = new SoulFire();
        return singleton;
    }
}
