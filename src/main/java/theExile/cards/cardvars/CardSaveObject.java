package theExile.cards.cardvars;

import theExile.cards.AbstractExileCard;

import java.io.Serializable;
import java.util.ArrayList;

public class CardSaveObject implements Serializable {
    public ArrayList<AbstractExileCard.elenum> elements = new ArrayList<>();
    public boolean sigil = false;
    public boolean retain = false;
    public boolean debuffIncrease = false;
    public int baseDamage = 0;
    public int baseBlock = 0;
    public int baseMagic = 0;
    public int baseSecondMagic = 0;
    public int baseThirdMagic = 0;
}
