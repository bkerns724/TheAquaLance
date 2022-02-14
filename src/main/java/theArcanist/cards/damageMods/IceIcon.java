package theArcanist.cards.damageMods;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class IceIcon extends AbstractCustomIcon {
    private static IceIcon singleton;
    public static final String ID = ArcanistMod.makeID("Ice");
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/Ice.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

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
