package theAquaLance.cards;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theAquaLance.AquaLanceMod;
import theAquaLance.powers.SoakedPower;

import java.util.ArrayList;
import java.util.Random;

import static theAquaLance.AquaLanceMod.makeID;
import static theAquaLance.util.Wiz.*;

public class Rapids extends AbstractEasyCard {
    public final static String ID = makeID("Rapids");
    private final static int DAMAGE = 10;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 3;
    private final static int UPGRADE_MAGIC = 1;

    public Rapids() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AquaLanceMod.Enums.WATER);
        for (AbstractMonster mo : getEnemies()) {
            applyToEnemy(mo, new SoakedPower(mo, magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}