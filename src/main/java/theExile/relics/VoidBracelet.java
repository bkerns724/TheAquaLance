package theExile.relics;

import theExile.TheExile;

import static theExile.ExileMod.makeID;

//  in EldritchDamage
public class VoidBracelet extends AbstractExileRelic {
    public static final String ID = makeID(VoidBracelet.class.getSimpleName());

    public VoidBracelet() {
        super(ID, RelicTier.SPECIAL, LandingSound.MAGICAL, TheExile.Enums.EXILE_BROWN_COLOR);
        setUpdatedDescription();
    }
}
