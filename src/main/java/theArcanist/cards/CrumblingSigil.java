package theArcanist.cards;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.damagemods.ScourgeType;
import theArcanist.powers.DecayingPower;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class CrumblingSigil extends AbstractArcanistCard {
    public final static String ID = makeID(CrumblingSigil.class.getSimpleName());
    private final static int MAGIC = 5;
    private final static int UPGRADE_MAGIC = 2;

    public CrumblingSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    @Override
    protected void applyAttributes() {
        baseMagicNumber = magicNumber = MAGIC;
        magicOneIsDebuff = true;
        sigil = true;
        hasScourge = true;
        DamageModifierManager.addModifier(this, new ScourgeType());
    }

    @Override
    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(monster -> {
            if (getJinxAmountCard(monster) > 0)
                applyToEnemy(monster, new DecayingPower(monster, magicNumber*getJinxAmountCard(monster)));
        });
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}