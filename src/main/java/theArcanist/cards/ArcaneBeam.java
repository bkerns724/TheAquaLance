package theArcanist.cards;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import theArcanist.ArcanistMod;

import java.awt.*;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.*;

public class ArcaneBeam extends AbstractArcanistCard {
    public final static String ID = makeID("ArcaneBeam");
    private final static int DAMAGE = 22;
    private final static int UPGRADE_DAMAGE = 6;
    private final static int COST = 2;

    public ArcaneBeam() {
        super(ID, COST, CardType.ATTACK, ArcanistMod.Enums.UNIQUE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        MindblastEffect effect = new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal);
        ReflectionHacks.setPrivate(effect, AbstractGameEffect.class, "color", Color.MAGENTA.cpy());
        vfx(effect, 0.1F);
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}