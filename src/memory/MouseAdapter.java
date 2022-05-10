package memory;

import java.awt.event.MouseEvent;

public class MouseAdapter extends java.awt.event.MouseAdapter {
    private final Card adaptee;
    protected MouseAdapter(Card adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        adaptee.mousePressed(event);
    }
}
