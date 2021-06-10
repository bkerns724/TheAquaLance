package theAquaLance.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.actions.EmbedAction;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class MiniatureShards extends AbstractEmbedCard implements StartupCard {
    public final static String ID = makeID("MiniatureShards");
    private final static int DAMAGE = 3;
    private final static int MAGIC = 2;

    public MiniatureShards() {
        super(ID, 0, CardRarity.UNCOMMON);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EmbedAction(this, m));
    }

    public void upp() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
        AbstractCardField.replenish.set(this, true);
    }

    public boolean atBattleStartPreDraw() {
        att(new MakeTempCardInDrawPileAction(this, magicNumber, true, true, false));
        return false;
    }
}