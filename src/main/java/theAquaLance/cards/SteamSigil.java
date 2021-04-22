package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.patches.AbstractCardPatch.AbstractCardField;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class SteamSigil extends AbstractEasyCard {
    public final static String ID = makeID("SteamSigil");
    private final static int MAGIC = 10;
    private final static int UPGRADE_MAGIC = 3;

    public SteamSigil() {
        super(ID, -2, CardType.ATTACK, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCardField.sigil.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void onManualDiscard() {
        AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(true);
        if (mo != null)
            atb(new LoseHPAction(mo, adp(), magicNumber, AbstractGameAction.AttackEffect.FIRE));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}