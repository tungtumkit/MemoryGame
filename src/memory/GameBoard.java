package memory;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class GameBoard extends javax.swing.JPanel implements CardListener {
    private final int NUMBER_OF_PAIRS = 10,
                      NUMBER_OF_CARDS = 2*NUMBER_OF_PAIRS;
    
    private final String PATH = "src/data/",
                         IMAGENAME = "card_image_",
                         BACKFACE = "card_back",
                         IMAGETYPE = ".png";
    
    private final Card[] cards = new Card[NUMBER_OF_CARDS];
    private Card card1, card2;
    private int currentNumberOfPairs, flips;
    private long startTime;
    
    public GameBoard() {
        super();
        initComponents();
        setLayout(new GridLayout(4,5,5,5));
        init();
    }
    
    private void init() {
        //cards-Feld erzeugen
        int id=-1;
        for(int j=0; j< NUMBER_OF_PAIRS; j++)
            for(int i=0; i<2; i++) {
                try {
                    id++;
                    cards[id] = new Card(PATH + IMAGENAME + j + IMAGETYPE,
                                         PATH + BACKFACE + IMAGETYPE, this, j);
                }
                catch (Exception e) {
                    System.out.println("Error creating new card.");
                }
            }
        
        //Shuffle, then add the cards to gameboard layout
        Collections.shuffle(Arrays.asList(cards));
        for(int i=0; i<NUMBER_OF_CARDS; i++)
            this.add(cards[i]);
        
        card1 = card2 = null;
        flips = currentNumberOfPairs = 0;
        startTime = System.currentTimeMillis();
    }
    
    public void reset() {
        removeAll();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(750, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    public void turned(CardEvent e) {
        //if the last 2 cards are not one pair, turn them face-down
        if (card1 != null && card2 != null) {
            card1.setPlayable(true);
            card1.turn(false);
            
            card2.setPlayable(true);
            card2.turn(false);
            
            card1 = card2 = null;
        }
        
        if (card1 == null) {
            card1 = e.card;
            card1.setPlayable(false);
        }
        else {    //flip second card
            flips++;
            card2 = e.card;
            card2.setPlayable(false);
            
            if (card1.getPair() == card2.getPair()) {
                card1.setFinished();
                card2.setFinished();
                currentNumberOfPairs++;
                if (currentNumberOfPairs == NUMBER_OF_PAIRS) {
                    long time = System.currentTimeMillis() - startTime;
                    WinDialog dialog = new WinDialog((int)(time/1000), flips*2);
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
