package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.damageMods.ScourgeType;
import theArcanist.powers.CrushedPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class HeavySigil extends AbstractArcanistCard {
    public final static String ID = makeID(HeavySigil.class.getSimpleName());
    private final static int MAGIC = 1;
    private final static int UPGRADE_MAGIC = 1;

    public HeavySigil() {
        super(ID, -2, CardType.SKILL,CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(monster -> {
            int amount = getJinxAmount(monster);
            if (amount > 0)
                applyToEnemy(monster, new CrushedPower(monster, amount * magicNumber));
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}