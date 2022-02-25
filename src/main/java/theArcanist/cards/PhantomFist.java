package theArcanist.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.ArcanistMod;
import theArcanist.cards.damageMods.ForceDamage;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class PhantomFist extends AbstractArcanistCard {
    public final static String ID = makeID("PhantomFist");
    private final static int DAMAGE = 9;
    private final static int UPGRADE_DAMAGE = 3;
    private final static int MAGIC = 1;
    private final static int COST = 1;

    public PhantomFist() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        DamageModifierManager.addModifier(this, new ForceDamage());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, ArcanistMod.Enums.FIST, Color.PINK.cpy());
        atb(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}