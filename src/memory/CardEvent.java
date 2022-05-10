package memory;

import java.util.EventObject;

public class CardEvent extends EventObject {
    protected Card card;
    public CardEvent(Card src) {
        super(src);
        card = src;
    }
}
