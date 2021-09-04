package theAquaLance.cards;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

@AutoAdd.Ignore
public abstract class AbstractEmbedCard extends AbstractEasyCard {
    public boolean hitArtifact = false;
    public boolean missedMonsters = false;
    public boolean purgeOnExit = false;

    public AbstractEmbedCard(final String cardID, final int cost, final CardRarity rarity) {
        super(cardID, cost, CardType.ATTACK, rarity, CardTarget.ENEMY);
    }

    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Shard");
        return retVal;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> retVal = new ArrayList<>();
        retVal.add(new TooltipInfo(BaseMod.getKeywordTitle("aqualancemod:Shard"),
                BaseMod.getKeywordDescription("aqualancemod:Shard")));
        return retVal;
    }

    public abstract void use(AbstractPlayer p, AbstractMonster m);

    public void atStartOfTurn(AbstractCreature host) {}

    public void onAttacked(AbstractCreature attacker, AbstractCreature host, DamageInfo.DamageType type) {}

    public void onDiscard(AbstractCreature host) {}

    public void onDiscardSigil(AbstractCreature host) {}

    public void onReceivePower(AbstractCreature host, AbstractPower power,
                               AbstractCreature target, AbstractCreature source) {}

    public void onPop(AbstractCreature host) {}

    public void onCardDraw(AbstractCreature host) {}

    public abstract String getDesc();
}