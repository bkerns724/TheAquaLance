package theExile.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theExile.ExileMod;
import theExile.actions.ThornsDamageAction;
import theExile.cards.AbstractExileCard;
import theExile.damagemods.DeathLightningDamage;
import theExile.damagemods.EldritchDamage;
import theExile.damagemods.PhantasmalDamage;
import theExile.damagemods.IceDamage;

import static theExile.ExileMod.makeID;
import static theExile.util.Wiz.*;

public class ElementalProwessPower extends AbstractExilePower {
    public static String POWER_ID = makeID(ElementalProwessPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ElementalProwessPower(int amount) {
        super(POWER_ID, PowerType.BUFF, false, adp(), amount);
        this.name = NAME;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!(card instanceof AbstractExileCard) || card.type != AbstractCard.CardType.ATTACK)
            return;

        AbstractExileCard card2 = (AbstractExileCard) card;

        for (AbstractExileCard.elenum e : card2.damageModList) {
            if (e == AbstractExileCard.elenum.ICE || e == AbstractExileCard.elenum.FAKE_ICE) {
                DamageModContainer container = new DamageModContainer(this, new IceDamage());
                DamageInfo info = BindingHelper.makeInfo(container, adp(), amount, DamageInfo.DamageType.THORNS);
                forAllMonstersLiving(mon -> atb(new ThornsDamageAction(mon, info, ExileMod.Enums.ICE)));
            }
            if (e == AbstractExileCard.elenum.PHANTASMAL || e == AbstractExileCard.elenum.FAKE_PHANTASMAL) {
                DamageModContainer container = new DamageModContainer(this, new PhantasmalDamage());
                DamageInfo info = BindingHelper.makeInfo(container, adp(), amount, DamageInfo.DamageType.THORNS);
                forAllMonstersLiving(mon -> atb(new ThornsDamageAction(mon, info, ExileMod.Enums.FORCE)));
            }
            if (e == AbstractExileCard.elenum.ELDRITCH || e == AbstractExileCard.elenum.FAKE_ELDRITCH) {
                DamageModContainer container = new DamageModContainer(this, new EldritchDamage());
                DamageInfo info = BindingHelper.makeInfo(container, adp(), amount, DamageInfo.DamageType.THORNS);
                forAllMonstersLiving(mon -> atb(new ThornsDamageAction(mon, info, ExileMod.Enums.ELDRITCH)));
            }
            if (e == AbstractExileCard.elenum.LIGHTNING || e == AbstractExileCard.elenum.FAKE_LIGHTNING) {
                DamageModContainer container = new DamageModContainer(this, new DeathLightningDamage());
                forAllMonstersLiving(mon -> {
                    if (mon.hasPower(VulnerablePower.POWER_ID))
                        amount = (int)(amount * 1.5f);
                    DamageInfo info = BindingHelper.makeInfo(container, adp(), amount, DamageInfo.DamageType.THORNS);
                    atb(new ThornsDamageAction(mon, info, ExileMod.Enums.LIGHTNING_S));
                });
            }
        }
    }
}