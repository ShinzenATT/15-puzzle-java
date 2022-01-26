import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This is a cell of the grid in the 15 game. It is an extension of JButton that holds an int value and requires an actionListener.
 */
public class Cell extends JButton {
    /** the number value that the cell holds */
    private int value;

    public Cell(int text, ActionListener handler){
        super();
        this.setNum(text);
        value = text;
        super.addActionListener(handler);
    }

    /**
     * Stores the number in the cell and displays it on the button. The button is empty when the number is below 1.
     * @param num An int value to be displayed
     */
    public void setNum(int num) {
        if(num <= 0){
            super.setText("");
        } else {
            super.setText(String.valueOf(num));
        }
        value = num;
    }

    /**
     * Returns the number value stored and displayed on the cell
     */
    public int getNum() {
        return value;
    }
}
