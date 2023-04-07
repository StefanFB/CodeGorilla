package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    public ConnectFour() {
        int rows = 6;
        int cols = 7;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 560);
        setVisible(true);
        setLayout(new GridLayout(rows, cols, 0, 0));
        setTitle("Connect Four");

        // Create loop to create new buttons with the correct cell name
        for (int i = rows; i > 0; i--) {
            for (char c = 'A'; c < 'A' + cols; c++) {
                String buttonName = String.format("Button%c%d", c, i);
                String buttonFace = String.format("%c%d", c, i);
                // System.out.println(buttonName);
                JButton button = new JButton(buttonFace);
                button.setName(buttonName);
                button.setFocusPainted(false);
                add(button);
            }
        }
    }
}
