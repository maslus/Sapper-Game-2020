import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;

public class Sapper extends JFrame {
    
    private Game game;
    private JPanel panel;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 81;
    private final int IMAGE_SIZE = 50;
    
    
    public static void main (String[] args) {
        new Sapper();
        
    }
    
    private Sapper () {
        game=new Game(COLS,ROWS,BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }
    
    private void initPanel () {
        panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }
    
    private void initFrame () {
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sapper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
    }
    
    public void setImages () {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }
    
    private Image getImage (String name) {
        String    filename = "img/" + name + ".png";
        ImageIcon icon     = new ImageIcon(getClass().getResource(filename));   //  RES -> mark directory as -> resources root
        return icon.getImage();
    }
}
