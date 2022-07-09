package theArcanist.events;

import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

public abstract class AbstractArcanistEvent extends AbstractImageEvent {
    public String name;
    public String[] descriptions;
    public String[] options;

    protected int amount;
    protected int amount2;

    public AbstractArcanistEvent(EventStrings eventStrings, String imagePath, int amount, int amount2) {
        super(eventStrings.NAME, eventStrings.DESCRIPTIONS[0], imagePath);
        noCardsInRewards = true;
        this.amount = amount;
        this.amount2 = amount2;
        name = eventStrings.NAME;
        descriptions = eventStrings.DESCRIPTIONS;
        options = eventStrings.OPTIONS;
        for (int i = 0; i < options.length; i++) {
            options[i] = options[i].replace("!E!", String.valueOf(amount));
            options[i] = options[i].replace("!E2!", String.valueOf(amount2));
        }
    }

    public AbstractArcanistEvent(EventStrings eventStrings, String imagePath, int amount) {
        this(eventStrings, imagePath, amount, -1);
    }

    public AbstractArcanistEvent(EventStrings eventStrings, String imagePath) {
        this(eventStrings, imagePath, -1, -1);
    }
}
