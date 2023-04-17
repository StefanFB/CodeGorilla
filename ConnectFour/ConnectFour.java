package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ConnectFour extends JFrame implements ActionListener {

    GameState turn = GameState.PLAYER_1;
    private final int rows = 6;
    private final int cols = 7;

    // Create a buttonMap Map<JButton, Integer> to store all buttons in
    public Map<JButton, Integer> buttonMap = new HashMap<>();

    public ConnectFour() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 480);
        setLayout(new GridLayout(rows, cols, 0, 0));
        setLocationRelativeTo(null);
        setTitle("Connect Four");
        setVisible(true);

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

                // Update the Map to keep track of which cell is to be filled next
                buttonMap.put(button, 0);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
         * Determine which column was clicked, iterate over that column
         * Use fillNextCell to check which is the next cell to fill
         * When the next cell has been updated, stop the iterating
         */

        JButton clickedButton = (JButton) e.getSource();

        // Get which column was clicked
        String column = String.valueOf(clickedButton.getName().charAt(6));
        // System.out.println("Column clicked: " + column); // DEBUG PRINT STATEMENT

        // Iterate over each cell in the column and try to fill the next cell with the correct value
        int row = 1;
        for (int i = 0; i < rows; i++) {
            int filledCell = fillNextCell(column, row);
            if (filledCell == 0) break;
            row++;
        }
    }

    private int fillNextCell(String column, int row) {
        // Create buttonName to compare to button.getName()
        String buttonName = String.format("Button%s%d", column, row);

        // Iterate over all buttons to find the next button in the clicked column
        for (Map.Entry<JButton, Integer> m : buttonMap.entrySet()) {
            if (m.getKey().getName().equals(buttonName)) {

                /*
                * If the next button has not yet been changed (i.e. value == 0), update it
                * value = 1 for player 1, value = 2 for player 2
                * set label to display the correct symbol
                * Switch the player's turn to the other player
                * Return 0 to signal that we have successfully filled a cell
                 */
                if (m.getValue() == 0) {

                    // Put in a 'X' or 'O' depending on who's turn it is
                    if (turn == GameState.PLAYER_1) {
                        m.getKey().setLabel("X");
                        m.setValue(1);
                        turn = GameState.PLAYER_2;
                    } else {
                        m.getKey().setLabel("O");
                        m.setValue(2);
                        turn = GameState.PLAYER_1;
                    }
                    return 0;
                }
            }
        }
        return 1; // if no cell was updated, signal that we should try the next cell
    }
}
