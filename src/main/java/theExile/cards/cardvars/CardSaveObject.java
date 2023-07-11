package theExile.cards.cardvars;

import theExile.cards.AbstractExileCard;

import java.io.Serializable;
import java.util.ArrayList;

public class CardSaveObject implements Serializable {
    public ArrayList<AbstractExileCard.elenum> elements = new ArrayList<>();
    public boolean sigil = false;
    public boolean misc = false;
    public int cost;
}
