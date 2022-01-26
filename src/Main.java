import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static final int width = 160;
    public static final int height = width + 20;
    public static final int scale = 3;
    public static final String name = "15 Game";
    /** coordinates of the empty space */
    private Point emptySpace = new Point(0, 0);
    /** the matrix for all the cell buttons */
    private final Cell[][] matrix = new Cell[4][4];
    /** the cheat counter */
    private int cheat = 0;

    public Main(){
        super(name);
        // Set windows size and make it unresizable
        setMinimumSize(new Dimension(width * scale, height * scale));
        setMaximumSize(new Dimension(width * scale, height * scale));
        setPreferredSize(new Dimension(width * scale, height * scale));
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the game-board and seed the cells
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
                    // On click event handler
                    (e -> {
                        // move value if it is adjacent (not diagonal) to the empty space
                        if ((Math.abs(x - emptySpace.x) == 1 && Math.abs(y - emptySpace.y) == 0) || (Math.abs(x - emptySpace.x) == 0 && Math.abs(y - emptySpace.y) == 1)){
                            matrix[emptySpace.x][emptySpace.y].setNum(matrix[x][y].getNum());
                            matrix[x][y].setNum(0);
                            emptySpace = new Point(x, y);

                            // Display a message box when the game has been won
                            if(winConditionCheck()){
                                JOptionPane.showMessageDialog(this, "Grattis! Du har vunnit!");
                            }
                        }
                        // increment the cheat counter if the empty space is clicked
                        else if(emptySpace.x == x && emptySpace.y == y){
                            cheat++;
                            if(cheat == 10){
                                commenceCheat();
                            }
                            Toolkit.getDefaultToolkit().beep();
                        }
                        // Beep when the wrong buttons are clicked
                        else {
                            Toolkit.getDefaultToolkit().beep();
                        }
                    })
                    );

            i++;
            // Added cell butoon to the window grid
            add(matrix[x][y]);
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Checks if the cell numbers are in order
     * @return returns true when the cell numbers are in order
     */
    private boolean winConditionCheck(){
        for (int i = 0; i < 15; i++){
            if(matrix[i % 4][i / 4].getNum() != (i + 1)){
                return false;
            }
        }
        return true;
    }

    /**
     * When the empty space is clicked 10 times then set the game-board to an almost complete state
     */
    private void commenceCheat(){
        for (int i = 0; i < 14; i++){
            matrix[i % 4][i / 4].setNum(i + 1);
        }
        matrix[2][3].setNum(0);
        emptySpace = new Point(2, 3);
        matrix[3][3].setNum(15);
    }

    // Create the window
    public static void main(String[] args){
        new Main();
    }
}
