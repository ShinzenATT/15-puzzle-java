import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Cell extends JButton {
    private int value;
    private Point cords;
    public Cell(int text, ActionListener handler){
        super();
        this.setNum(text);
        value = text;
        super.addActionListener(handler);
    }

    public void setNum(int text) {
        if(text <= 0){
            super.setText("");
        } else {
            super.setText(String.valueOf(text));
        }
        value = text;
    }

    public int getNum() {
        return value;
    }
}
