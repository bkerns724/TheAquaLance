package theArcanist.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class AdrenalineSigil extends AbstractSigilCard {
    public final static String ID = makeID("AdrenalineSigil");
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public AdrenalineSigil() {
        super(ID, CardRarity.RARE, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void onManualDiscard() {
        applyToSelf(new ArtifactPower(AbstractDungeon.player, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}