package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theAquaLance.actions.EmbedAction;
import theAquaLance.powers.HobbledPower;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class WildStabs extends AbstractEasyCard {
    public final static String ID = makeID("WildStabs");
    private final static int DAMAGE = 4;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;
    private final static int SECOND_MAGIC = 1;

    public WildStabs() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToEnemy(m, new WeakPower(m, secondMagic, false));
        if (upgraded) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            applyToEnemy(m, new HobbledPower(m, secondMagic));
        }
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToEnemy(m, new VulnerablePower(m, secondMagic, false));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}