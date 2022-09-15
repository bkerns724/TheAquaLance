package theExile.orbs;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface OnAttackOrb {
    public void onAttack(AbstractMonster mon, DamageInfo info);
}
