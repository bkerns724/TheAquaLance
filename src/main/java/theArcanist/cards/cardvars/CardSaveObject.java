package theArcanist.cards.cardvars;

import theArcanist.cards.AbstractArcanistCard;

import java.io.Serializable;
import java.util.ArrayList;

public class CardSaveObject implements Serializable {
    public ArrayList<AbstractArcanistCard.elenum> elements = new ArrayList<>();
    public boolean sigil = false;
    public boolean retain = false;
    public boolean debuffIncrease = false;
    public boolean scourgeIncrease = false;
    public int baseDamage = 0;
    public int baseBlock = 0;
    public int baseMagic = 0;
    public int baseSecondMagic = 0;
}
