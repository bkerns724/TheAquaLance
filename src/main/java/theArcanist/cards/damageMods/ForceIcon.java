package theArcanist.cards.damageMods;

import IconsAddon.icons.AbstractCustomIcon;
import IconsAddon.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import theArcanist.ArcanistMod;

public class ForceIcon extends AbstractCustomIcon {
    private static ForceIcon singleton;
    public static final String ID = ArcanistMod.makeID("Force");
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Force.png";
    private static final Texture TEXTURE =
            TextureLoader.getTexture(TEXTURE_STRING);

    private ForceIcon() {
        super(ID, TEXTURE);
    }

    public static ForceIcon get()
    {
        if (singleton == null) {
            singleton = new ForceIcon();
        }
        return singleton;
    }
}
