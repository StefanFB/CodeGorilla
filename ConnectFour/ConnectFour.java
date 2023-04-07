package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFour extends JFrame implements ActionListener {

    GameState turn = GameState.PLAYER_1;
    public ConnectFour() {
        int rows = 6;
        int cols = 7;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 480);
        setVisible(true);
        setLayout(new GridLayout(rows, cols, 0, 0));
        setLocationRelativeTo(null);
        setTitle("Connect Four");

        // Create loop to create new buttons with the correct cell name
        for (int i = rows; i > 0; i--) {
            for (char c = 'A'; c < 'A' + cols; c++) {
                String buttonName = String.format("Button%c%d", c, i);
                String buttonFace = String.format(" ");

                // Create button with name "Button<char><int>" and empty label
                JButton button = new JButton(buttonFace);
                button.setName(buttonName);
                button.setFocusPainted(false);
                button.addActionListener(this);
                add(button);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Put in a 'X' or 'O' depending on who's turn it is
        if (turn == GameState.PLAYER_1) {
            clickedButton.setLabel("X");
            turn = GameState.PLAYER_2;
        } else {
            clickedButton.setLabel("O");
            turn = GameState.PLAYER_1;
        }
    }
}
