package theArcanist.cards;

import basemod.AutoAdd;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static theArcanist.ArcanistMod.makeID;

@AutoAdd.Ignore
public class Write extends AbstractArcanistCard {
    public final static String ID = makeID("Write");
    private final static int COST = 0;

    public Write() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        color = CardColor.COLORLESS;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        crack("C:\\Users\\15024\\Desktop\\Mods\\OtherMod\\STS\\powers\\powers.atlas",
                "C:\\Users\\15024\\Desktop\\Mods\\Arcanist\\src\\main\\resources\\arcanistmodResources\\images\\powerImages\\");
    }

    public void upp() {
    }

    public static void crack(String srcAtlas, String dstDir) {
        try {
            FileHandle fh = Gdx.files.absolute(srcAtlas);
            TextureAtlas.TextureAtlasData data = new TextureAtlas.TextureAtlasData(fh, fh.parent(), false);
            File dir = new File(dstDir);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("mkdirs:" + dstDir);
            }
            for (TextureAtlas.TextureAtlasData.Region region : data.getRegions()) {
                File file = region.page.textureFile.file();
                BufferedImage root = ImageIO.read(file);
                String fileName = region.name;
                int width = region.width;
                int height = region.height;
                int x = region.left;
                int y = region.top;
                BufferedImage canvas;
                canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                canvas.getGraphics().drawImage(root, 0, 0, width, height, x, y, x + width, y + height, null);
                ImageIO.write(canvas, "png", new File(dstDir + fileName + ".png"));
                System.out.println("Proccess to " + dstDir + fileName + ".png");
            }
        }
        catch (Exception e) {
        }
    }
}