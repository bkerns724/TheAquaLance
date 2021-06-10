package theAquaLance.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theAquaLance.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractEmbedCard extends AbstractEasyCard {
    public boolean hitArtifact = false;
    public boolean missedMonsters = false;

    public AbstractEmbedCard(final String cardID, final int cost, final CardRarity rarity) {
        super(cardID, cost, CardType.ATTACK, rarity, CardTarget.ENEMY);
    }

    public abstract void use(AbstractPlayer p, AbstractMonster m);

    public abstract void upp();

    public int getJaggedAmount() {return 0;}

    public int getMarkAmount() {return 0;}

    public int getTriggerAmount() {return 0;}

    public int getLeechAmount() {return 0;}

    public int getDamageAmount() {return 0;}

    public int getSapAmount() {return 0; }

    public int getDiscardDamAmount() {return 0;}

    public int getCatalystAmount() {return 0;}

    public int getFountainAmount() {return 0;}

    public void onFinisher(AbstractCreature host) {}
}