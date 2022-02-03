package theArcanist.cards.damageMods;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class DarkIcon extends AbstractCustomIcon {
    private static DarkIcon singleton;
    public static final String ID = ArcanistMod.makeID("Dark");
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Dark.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);

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
