package theArcanist.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theArcanist.damagemods.SoulFireDamage;
import theArcanist.powers.KindlingPower;

import java.util.ArrayList;
import java.util.List;

import static theArcanist.ArcanistMod.makeID;
import static theArcanist.util.Wiz.applyToEnemy;
import static theArcanist.util.Wiz.forAllMonstersLiving;

public class SmokingSigil extends AbstractArcanistCard {
    public final static String ID = makeID(SmokingSigil.class.getSimpleName());
    private final static int MAGIC = 100;
    private final static int UPGRADE_MAGIC = 50;

    public SmokingSigil() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        magicOneIsDebuff = true;
        baseMagicNumber = magicNumber = MAGIC;
        sigil = true;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        CardStrings fireStrings = SoulFireDamage.cardStrings;
        ArrayList<TooltipInfo> list = new ArrayList<>();
        list.add(new TooltipInfo(fireStrings.DESCRIPTION, fireStrings.EXTENDED_DESCRIPTION[0]));
        return list;
    }

    public void onUse(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(mon -> applyToEnemy(mon, new KindlingPower(mon, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}