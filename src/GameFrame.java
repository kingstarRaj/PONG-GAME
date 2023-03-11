import javax.swing.*;
import java.awt.*;

public class GameFrame {
    JFrame frame;

    //Panel
    GamePanel panel;

    GameFrame(){
        frame = new JFrame("PONG GAME");
        // to allow edition inn frame
        // frame.setLayout(null);

        panel = new GamePanel();
        // panel.setBounds(0,0,1000,555);
        frame.add(panel);
        frame.setBackground(Color.green);
        // we don't want to give a predefined size to the frame ,so we use pack()
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setSize(1000,555);

        frame.setVisible(true);
        frame.setResizable(false);
        //to fill the place of line 13 we use below methode to give edition and also can overlap the frame on frame by this methode
        frame.setLocationRelativeTo(null);

    }
}
