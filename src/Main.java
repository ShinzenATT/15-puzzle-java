import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static final int width = 160;
    public static final int height = width + 20;
    public static final int scale = 3;
    public static final String name = "15 Game";

    public Main(){
        setMinimumSize(new Dimension(width * scale, height * scale));
        setMaximumSize(new Dimension(width * scale, height * scale));
        setPreferredSize(new Dimension(width * scale, height * scale));

        JFrame frame = new JFrame(name);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new Main();
    }
}
