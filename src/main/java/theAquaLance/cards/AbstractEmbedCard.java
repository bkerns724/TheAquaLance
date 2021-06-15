package theAquaLance.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theAquaLance.powers.EmbedPower;

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

    public void atStartOfTurn(EmbedPower pow) {}

    public void onAttacked(AbstractCreature attacker) {}

    public void onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {}

    public float atDamageGive(float damage, DamageInfo.DamageType type) {return damage;}

    public float getDamageModifier() {return 0.0F;}

    public void onPopped(AbstractCreature host) {}
}