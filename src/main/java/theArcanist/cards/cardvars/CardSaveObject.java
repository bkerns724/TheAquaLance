package theArcanist.cards.cardvars;

import theArcanist.cards.AbstractArcanistCard;

import java.io.Serializable;
import java.util.ArrayList;

public class CardSaveObject implements Serializable {
    public ArrayList<AbstractArcanistCard.elenum> elements = new ArrayList<>();
    public boolean sigil = false;
    public boolean retain = false;
    public boolean scourgeIncrease = false;
    public boolean debuffIncrease = false;
    public int extraEnergy = 0;
    public int extraDraw = 0;
    public int damageBonus = 0;
}
