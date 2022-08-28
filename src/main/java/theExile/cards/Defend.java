package theExile.cards;

import static theExile.ExileMod.makeID;

public class Defend extends AbstractExileCard {
    public final static String ID = makeID(Defend.class.getSimpleName());
    public final static int BLOCK = 5;
    public final static int UPGRADE_BLOCK = 3;

    public Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
    }

    @Override
    protected void applyAttributes() {
        baseBlock = BLOCK;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void nonTargetUse() {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}