package memory;

import java.util.EventListener;
        
public interface CardListener extends EventListener {
    void turned(CardEvent event);
}
