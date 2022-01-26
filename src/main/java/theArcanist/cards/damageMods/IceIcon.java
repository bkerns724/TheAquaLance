package theArcanist.cards.damageMods;

import IconsAddon.icons.AbstractCustomIcon;
import IconsAddon.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import theArcanist.ArcanistMod;

public class IceIcon extends AbstractCustomIcon {
    private static IceIcon singleton;
    public static final String ID = ArcanistMod.makeID("Ice");
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Ice.png";
    private static final Texture TEXTURE =
            TextureLoader.getTexture(TEXTURE_STRING);

    private IceIcon() {
        super(ID, TEXTURE);
    }

    public static IceIcon get()
    {
        if (singleton == null) {
            singleton = new IceIcon();
        }
        return singleton;
    }
}
