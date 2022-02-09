package theArcanist.cards.damageMods;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theArcanist.ArcanistMod;
import theArcanist.util.TexLoader;

public class SoulFireIcon extends AbstractCustomIcon {
    private static SoulFireIcon singleton;
    public static final String ID = ArcanistMod.makeID("SoulFire");
    private static final String TEXTURE_STRING = "arcanistmodResources/images/damageIcons/SoulFire.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);

    private SoulFireIcon() {
        super(ID, TEXTURE);
    }

    public static SoulFireIcon get()
    {
        if (singleton == null) {
            singleton = new SoulFireIcon();
        }
        return singleton;
    }
}
