package theExile.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theExile.powers.ShadowcloakPower;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.applyToSelf;

public class TranslucentSigil extends AbstractExileCard {
    public final static String ID = makeID(TranslucentSigil.class.getSimpleName());
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public TranslucentSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShadowcloakPower(AbstractDungeon.player, magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}