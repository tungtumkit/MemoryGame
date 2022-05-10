package memory;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MemoryGame  {
    public MemoryGame() {
        AppWindow window = new AppWindow();
        
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    public static void main(String[] agrs) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Unable to set LookAndFeel.");
        }
        
        new MemoryGame();
    }
}
