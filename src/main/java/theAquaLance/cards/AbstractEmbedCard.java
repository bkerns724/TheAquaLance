package theAquaLance.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theAquaLance.util.Wiz.*;

@AutoAdd.Ignore
public abstract class AbstractEmbedCard extends AbstractEasyCard {
    public boolean hitArtifact = false;
    public boolean missedMonsters = false;

    public AbstractEmbedCard(final String cardID, final int cost, final CardRarity rarity) {
        super(cardID, cost, CardType.ATTACK, rarity, CardTarget.ENEMY);
    }

    public abstract void use(AbstractPlayer p, AbstractMonster m);

    public void atStartOfTurn(AbstractCreature host) {}

    public void onShuffle(AbstractCreature host) {}

    public void onApplyPower(AbstractCreature host, AbstractPower power, AbstractCreature source, AbstractCreature target) {}

    public void onApplyStatus(AbstractCreature host, AbstractCard card) {}

    public void onAttacked(AbstractCreature attacker, AbstractCreature host, DamageInfo.DamageType type) {}

    public void onDiscardSigil(AbstractCreature host) {}

    public void onReceivePower(AbstractCreature host, AbstractPower power,
                               AbstractCreature target, AbstractCreature source) {}

    public abstract String getDesc();
}