package theArcanist.cards.damageMods;

import IconsAddon.icons.AbstractCustomIcon;
import IconsAddon.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import theArcanist.ArcanistMod;

public class DarkIcon extends AbstractCustomIcon {
    private static DarkIcon singleton;
    public static final String ID = ArcanistMod.makeID("Dark");
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Dark.png";
    private static final Texture TEXTURE =
            TextureLoader.getTexture(TEXTURE_STRING);

    private DarkIcon() {
        super(ID, TEXTURE);
    }

    public static DarkIcon get()
    {
        if (singleton == null) {
            singleton = new DarkIcon();
        }
        return singleton;
    }
}
