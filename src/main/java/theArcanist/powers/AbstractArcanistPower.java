package theArcanist.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.commons.lang3.math.NumberUtils;
import theArcanist.ArcanistMod;
import theArcanist.patches.AbstractPowerPatch;
import theArcanist.util.TexLoader;

import java.util.Arrays;
import java.util.regex.Pattern;

public abstract class AbstractArcanistPower extends AbstractPower {
    public int amount2 = -1;
    public boolean isTwoAmount = false;
    public static Color redColor2 = Color.RED.cpy();
    public static Color greenColor2 = Color.GREEN.cpy();
    public boolean canGoNegative2 = false;

    private static final String REGEX = "\\{!.*?!\\|.*?}";
    private static final String DYNAMIC_KEY = "{@@}";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public AbstractArcanistPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this.ID = id;
        this.isTurnBased = isTurnBased;
        this.owner = owner;
        this.amount = amount;
        this.type = powerType;

        String textureString = ArcanistMod.modID + "Resources/images/powers/" + ID.replaceAll(ArcanistMod.modID +":",  "") + "32.png";

        Texture normalTexture = TexLoader.getTexture(textureString);
        Texture hiDefImage = TexLoader.getTexture(ArcanistMod.modID + "Resources/images/powers/" + ID.replaceAll(ArcanistMod.modID +":", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }

    public void onManualDiscard() {}

    public void onDiscardSigil() {}

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (isTwoAmount) {
            if (!isTurnBased) {
                greenColor2.a = c.a;
                c = greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        } else if (amount2 < 0 && canGoNegative2) {
            redColor2.a = c.a;
            c = redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        }
    }

    public abstract void upDescription();

    @Override
    public void updateDescription() {
        upDescription();
        String[] words = description.split(" ");
        words = checkForUnwrapping(this, words);

        StringBuilder sbuilder = new StringBuilder();
        sbuilder.setLength(0);

        for (String word : words) {
            word = word.trim();
            if (!word.isEmpty()) {
                if (word.equals("!A!"))
                    sbuilder.append("#b").append(amount).append(" ");
                else if (word.equals("!A2!") && isTwoAmount)
                    sbuilder.append("#b").append(amount2).append(" ");
                else
                    sbuilder.append(word).append(" ");
            }
        }
        description = sbuilder.toString().trim();
    }

    // Thanks to Alison Moons for making Dynamic Text Blocks for cards.
    // I have copy pasted her code and modified it a bit for powers.
    public static String unwrap(AbstractArcanistPower pow, String key) {
        //Cut the leading { and the trailing } and then split the string along each |
        key = key.substring(1, key.length()-1);
        String[] parts = key.split("\\|");
        //Our first piece will always be the dynamic variable we care about. Find its value
        Integer var = null;
        if (parts[0].equals("!A!")) {
            //Uses !D! for damage, just like normal dynvars, same applies to !B! and !M!
            var = pow.amount;
        } else if (parts[0].equals("!A2!")) {
            var = pow.amount2;
        }

        //Clean up the first string since we don't need it
        parts = Arrays.copyOfRange(parts, 1, parts.length);
        //If we found a var then we can do stuff, else just return an empty string
        key = "";
        if (var != null) {
            //Define a matched flag. Lets us know to NOT overwrite the output string with the default output if it actually matched a case
            boolean matched = false;
            //Iterate each case. Note that the right most matching case will be the output string, or the default output if no cases match
            for (String s : parts) {
                //All cases need to contain a single =, which we use for splitting the case into its values and output
                if (s.contains("=")) {
                    String[] split = s.split("=");
                    //Check if the condition has commas, an indicator of a case having multiple conditions.
                    if (split[0].contains(",")) {
                        String[] numbers = split[0].split(",");
                        //Iterate each condition, as long as at least 1 condition matches we set the text
                        for (String n : numbers) {
                            if(checkMatch(var, n)) {
                                //Checking the length allows up to know if there is actual text or just an empty string
                                if (split.length > 1) {
                                    key = split[1];
                                } else {
                                    key = "";
                                }
                                matched = true;
                            }
                        }
                    } else {
                        //Else just check the condition directly
                        if (checkMatch(var, split[0])) {
                            if (split.length > 1) {
                                key = split[1];
                            } else {
                                key = "";
                            }
                            matched = true;
                        }
                    }
                    //If this is the default output designated by @= then set this as the output text if we haven't matched anything else yet.
                    if (split[0].equals("@") && !matched) {
                        if (split.length > 1) {
                            key = split[1];
                        } else {
                            key = "";
                        }
                    }
                }
            }
        }
        return key;
    }

    private static boolean checkMatch(Integer var, String s) {
        //Greater Than, Less Than, Divisible By, and Ends With are the 4 supported conditional checks, in addition to Direct Match, which has no symbol attached
        boolean greater = s.contains(">");
        boolean less = s.contains("<");
        boolean mod = s.contains("%");
        boolean ends = s.contains("&");
        //Remove all these special symbols before checking if the condition is a number we can make
        s = s.replace(">", "").replace("<", "").replace("%", "").replace("&","");
        if (NumberUtils.isCreatable(s)) {
            //Grab our var minus the number, helpful for comparisons
            int comp = var.compareTo(NumberUtils.createInteger(s));
            //Checks the Direct Match, Greater Than, and Less Than cases
            boolean signCheck = !greater && !less && comp == 0 || greater && comp > 0 || less && comp < 0;
            //Checks the Divisible By case. If the numbers are equal (comp is 0), or if variable mod N is 0, then its divisible
            boolean moduloCheck = mod && (comp == 0 || var % NumberUtils.createInteger(s) == 0);
            //Checks the Ends With case. If the numbers are equal, or if the trailing digits of comp are all 0's, then var ends with N
            boolean digitCheck = ends && (comp == 0 || comp % Math.pow(10, s.length()) == 0);
            //As long as at least one condition matches we are good
            return signCheck || moduloCheck || digitCheck;
        }
        //If it's not a creatable number, there was a format error. Just return false instead of blowing up
        return false;
    }

    private static String[] checkForUnwrapping(AbstractArcanistPower pow, String[] splitText) {
        //If the string contains the key, or is known to contain the key, we need to unpack it
        if (AbstractPowerPatch.DynamicTextField.isDynamic.get(pow)) {
            //Replace all the dynamic text blocks with their unwrapped equivalent
            java.util.regex.Matcher m = PATTERN.matcher(String.join(" ", splitText).replace(DYNAMIC_KEY,""));
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                m.appendReplacement(sb, unwrap(pow, m.group()));
            }
            m.appendTail(sb);
            return sb.toString().split(" ");
        } else if (Arrays.stream(splitText).anyMatch(s -> s.contains(DYNAMIC_KEY))) {
            AbstractPowerPatch.DynamicTextField.isDynamic.set(pow, true);
            return checkForUnwrapping(pow, splitText);
        } else {
            //Else just return the split
            return splitText;
        }
    }
}