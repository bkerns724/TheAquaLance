package theAquaLance.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theAquaLance.AquaLanceMod.makeID;
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

    public abstract String getEmbedDescription();

    public int onAttacked(DamageInfo info, int damage, AbstractCreature host) {return damage;}

    public int onAttackToChangeDamage(DamageInfo info, int damage) {return damage;}

    public void atStartOfTurn(AbstractCreature host) {}

    public void atStartOfPlayerTurn(AbstractCreature host) {}

    public int getPotency(AbstractCreature host) {return 1;}

    public void onDiscardWhileEmbed(AbstractCreature m) {}

    public void onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature host) {}

    public void onDeath() {}
}