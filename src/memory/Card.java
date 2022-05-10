package memory;

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.awt.Image;
import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class Card extends JComponent {
    private Image face, back, done;
    private boolean turned, playable, finished;
    private final int pair;    
    private final CardListener board;
    
    public Card(String faceFile, String backFile, CardListener board, int pair) {
        super();
        //load image files
        try {  
            done = Scalr.resize(ImageIO.read(new File("src/data/card_done.png")), 150);
            face = Scalr.resize(ImageIO.read(new File(faceFile)), 150);
            back = Scalr.resize(ImageIO.read(new File(backFile)), 150);
        }
        catch (IOException e)   {
            System.out.println("Error reading image file" + e);
        }
        
        this.pair = pair;
        
        turned = false;
        playable = true;
        finished = false;
        addMouseListener(new MouseAdapter(this));
        
        this.board = board;
    }
    
    protected void mousePressed(MouseEvent event) {
        turn(true);
    }
    
    @Override
    public synchronized void paint(Graphics g) {
        //be careful with this method, since it is continously invoked
        this.repaint(0L, 0, 0, 256, 256);
        super.paint(g);

        Image todraw = done;
        if (!finished)
            if (turned)
                todraw = face;
            else 
                todraw = back;
        g.drawImage(todraw, 0, 0, this);
    }
    
    //setters
    void setPlayable(boolean playable) {
        this.playable = playable;
    }
    
    public void turn(boolean up) {   
        if (!playable)  //only turn if card is in play
            return;
        
        turned = !turned;   
        paint(getGraphics());
        
        if (up)
            notifyCard(new CardEvent(this));
    }
    
    void setFinished() {
        finished = true;
        paint(getGraphics()); //always need to call this method
    }
    
    int getPair() {
        return pair;
    }
    
    protected synchronized void notifyCard(CardEvent event) {
        board.turned(event);
    }
}
