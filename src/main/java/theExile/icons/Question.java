package theExile.icons;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import theExile.ExileMod;
import theExile.util.TexLoader;

public class Question extends AbstractCustomIcon {
    private static Question singleton;
    public static final String ID = ExileMod.makeID(Question.class.getSimpleName());
    private static final String TEXTURE_STRING = "exilemodResources/images/damageIcons/Question.png";
    private static final Texture TEXTURE =
            TexLoader.getTexture(TEXTURE_STRING);
    public static final String CODE = "[" + ID + "Icon]";

    public Question() {
        super(ID, TEXTURE);
    }

    public static Question get()
    {
        if (singleton == null) {
            singleton = new Question();
        }
        return singleton;
    }
}
