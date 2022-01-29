package theArcanist.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.SPECIAL;
import static theArcanist.ArcanistMod.*;
import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class AcidSpray extends AbstractEasyCard {
    public final static String ID = makeID("AcidSpray");
    private final static int DAMAGE = 8;
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;
    private final static int COST = 1;

    public AcidSpray() {
        super(ID, COST, CardType.ATTACK, SPECIAL, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, Enums.ACID);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}