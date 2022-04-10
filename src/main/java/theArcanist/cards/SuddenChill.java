package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.damageMods.ScourgeType;
import theArcanist.powers.FrostbitePower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class SuddenChill extends AbstractArcanistCard {
    public final static String ID = makeID(SuddenChill.class.getSimpleName());
    private final static int DAMAGE = 9;
    private final static int COST = 1;
    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public SuddenChill() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
        magicOneIsDebuff = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
        addModifier(elenum.FAKE_ICE);
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.ICE);
        if (getJinxAmount(m) > 0)
            applyToEnemy(m, new FrostbitePower(m, getJinxAmount(m) * magicNumber));
    }

    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}