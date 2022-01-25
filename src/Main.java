import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Iterator;

public class Main extends JFrame {

    public static final int width = 160;
    public static final int height = width + 20;
    public static final int scale = 3;
    public static final String name = "15 Game";
    private Point emptySpace = new Point(0, 0);
    private final Cell[][] matrix = new Cell[4][4];
    private int cheat = 0;

    public Main(){
        super(name);
        setMinimumSize(new Dimension(width * scale, height * scale));
        setMaximumSize(new Dimension(width * scale, height * scale));
        setPreferredSize(new Dimension(width * scale, height * scale));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var it = new GameGenerator(4).iterator();
        setLayout(new GridLayout(4,4,3,3));

        int i = 0;
        while (it.hasNext()) {
            int num = it.next();
            int x = i % 4;
            int y = i / 4;
            if(num <= 0){
                emptySpace = new Point(x, y);
            }

            matrix[x][y] = new Cell(num,
                    (e -> {
                        if ((Math.abs(x - emptySpace.x) == 1 && Math.abs(y - emptySpace.y) == 0) || (Math.abs(x - emptySpace.x) == 0 && Math.abs(y - emptySpace.y) == 1)){
                            matrix[emptySpace.x][emptySpace.y].setNum(matrix[x][y].getNum());
                            matrix[x][y].setNum(0);
                            emptySpace = new Point(x, y);

                            if(winConditionCheck()){
                                JOptionPane.showMessageDialog(this, "Grattis! Du har vunnit!");
                            }
                        }
                        else if(emptySpace.x == x && emptySpace.y == y){
                            cheat++;
                            if(cheat == 10){
                                commenceCheat();
                            }
                        }
                        else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    })
                    );

            i++;
            add(matrix[x][y]);
        }

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean winConditionCheck(){
        for (int i = 0; i < 15; i++){
            if(matrix[i % 4][i / 4].getNum() != (i + 1)){
                return false;
            }
        }
        return true;
    }

    private void commenceCheat(){
        for (int i = 0; i < 14; i++){
            matrix[i % 4][i / 4].setNum(i + 1);
        }
        matrix[2][3].setNum(0);
        emptySpace = new Point(2, 3);
        matrix[3][3].setNum(15);
    }

    public static void main(String[] args){
        new Main();
    }
}
