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
    JLabel turnInfo = new JLabel();

    // Create a buttonMap Map<JButton, Integer> to store all buttons in
    public Map<JButton, Integer> buttonMap = new HashMap<>();

    // Create a winnerMap to store the winning combinations in
    public Map<JButton, Integer> winnerMap = new HashMap<>();

    public ConnectFour() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 520);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setTitle("Connect Four");
        setVisible(true);

        // Add top panel for the reset button, with specified maximum size
        JPanel topPanel = new JPanel();
        topPanel.setSize(560, 40);
        topPanel.setMaximumSize(new Dimension(560, 40));
        add(topPanel);

        // Add JLabel to display whose turn it is
        turnInfo.setText("Player 1's turn (X)");
        topPanel.add(turnInfo);

        // Add panel for the buttons, with GridLayout
        JPanel gamePanel = new JPanel();
        gamePanel.setSize(560, 480);
        gamePanel.setLayout(new GridLayout(rows, cols, 0, 0));
        add(gamePanel);

        // Add bottom panel for the reset button, with specified maximum size
        JPanel bottomPanel = new JPanel();
        bottomPanel.setSize(560, 40);
        bottomPanel.setMaximumSize(new Dimension(560, 40));
        add(bottomPanel);

        // Create loop to create new buttons with the correct cell name. Add to the gamePanel
        for (int i = rows; i > 0; i--) {
            for (char c = 'A'; c < 'A' + cols; c++) {
                String buttonName = String.format("Button%c%d", c, i);
                String buttonFace = " ";

                // Create button with name "Button<char><int>" and empty label
                JButton button = new JButton(buttonFace);
                button.setName(buttonName);
                button.setFocusPainted(false);
                button.addActionListener(this);
                button.setBackground(Color.lightGray);
                gamePanel.add(button);

                // Update the Map to keep track of which cell is to be filled next
                buttonMap.put(button, 0);
            }
        }

        // Add resetButton to the panel
        JButton ButtonReset = new JButton("Reset");
        ButtonReset.setName("ButtonReset");
        ButtonReset.setFocusPainted(false);
        ButtonReset.addActionListener(this);
        bottomPanel.add(ButtonReset);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
         * Determine which column was clicked, iterate over that column
         * Use fillNextCell to check which is the next cell to fill
         * When the next cell has been updated, stop the iterating
         */

        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton.getName().equals("ButtonReset")) {
            clearGameBoard();
        } else if (turn != GameState.END) {
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

                    // Put in a 'X' or 'O' depending on whose turn it is
                    if (turn == GameState.PLAYER_1) {
                        m.getKey().setLabel("X");
                        m.setValue(1);

                        // Check for a winning condition, else proceed to the next player
                        if (checkWinner(column, row)) {
                            turn = GameState.END;
                            for (Map.Entry<JButton, Integer> n : winnerMap.entrySet()) {
                                n.getKey().setBackground(Color.CYAN);
                            }
                            updateTurnInfo("Player 1 wins!");
                        } else {
                            turn = GameState.PLAYER_2;
                            updateTurnInfo();
                        }
                    } else {
                        m.getKey().setLabel("O");
                        m.setValue(2);
                        if (checkWinner(column, row)) {
                            turn = GameState.END;
                            for (Map.Entry<JButton, Integer> n : winnerMap.entrySet()) {
                                n.getKey().setBackground(Color.CYAN);
                            }
                            updateTurnInfo("Player 2 wins!");
                        } else {
                            turn = GameState.PLAYER_1;
                            updateTurnInfo();
                        }
                    }
                    return 0;
                }
            }
        }
        return 1; // if no cell was updated, signal that we should try the next cell
    }

    private boolean checkWinner(String column, int row) {
        int horizontalConnect = 0;
        int verticalConnect = 0;
        int diagonalConnect = 0;

        // Check for the current player, so we know what value to compare to (i.e. player 1 cell value = 1)
        int player = 0;
        if (turn == GameState.PLAYER_1) player = 1;
        else player = 2;

        // String[] that stores all column letters
        String[] columnLetters = new String[cols];
        for (int i = 0; i < cols; i++) {
            char ch = (char) ('A' + i);
            columnLetters[i] = Character.toString(ch);
        }

        // Check for horizontal four connected (iterate over the column letters)
        for (int i = 0; i < columnLetters.length; i++) {
            String buttonName = String.format("Button%s%d", columnLetters[i], row);

            // Iterate over all buttons. Find the value of the button to compare to the player's value
            for (Map.Entry<JButton, Integer> m : buttonMap.entrySet()) {
                if (m.getKey().getName().equals(buttonName)) {
                    if (m.getValue() == player) {
                        horizontalConnect++;
                        winnerMap.put(m.getKey(), m.getValue());
                    }

                    // If counter has not reached 4 yet, reset counter when the next is not the correct value
                    else if (horizontalConnect < 4) {
                        horizontalConnect = 0;
                        winnerMap.clear();
                    }
                }
            }
        }

        if (horizontalConnect >= 4) return true;

        // Check for vertical four connected (iterate over the row numbers)
        for (int i = 1; i <= rows; i++) {
            String buttonName = String.format("Button%s%d", column, i);

            // Iterate over all buttons. Find the value of the button to compare to the player's value
            for (Map.Entry<JButton, Integer> m : buttonMap.entrySet()) {
                if (m.getKey().getName().equals(buttonName)) {
                    if (m.getValue() == player) {
                        verticalConnect++;
                        winnerMap.put(m.getKey(), m.getValue());
                    }

                    // If counter has not reached 4 yet, reset counter when the next is not the correct value
                    else if (verticalConnect < 4) {
                        verticalConnect = 0;
                        winnerMap.clear();
                    }
                }
            }
        }

        if (verticalConnect >= 4) return true;

        // If there is no vertical connect four, check for diagonal up connect four

        // Check at what letter to start
        int startLetter = 0;
        for (int i = 0; i < columnLetters.length; i++) {
            if (columnLetters[i].equals(column)) {
                startLetter = i;
            }
        }

        // Check at what number to start
        int startNumber = row;

        // 'Go back' to the lowest possible combination of letter + number
        while (startLetter > 0 && startNumber > 1) {
            startLetter--;
            startNumber--;
        }

        for (int i = startLetter, j = startNumber; i < columnLetters.length; i++, j++) {
            if (j > rows) break;
            String buttonName = String.format("Button%s%d", columnLetters[i], j);
            // System.out.println(buttonName); // Debug print statement

            // Iterate over all buttons. Find the value of the button to compare to the player's value
            for (Map.Entry<JButton, Integer> m : buttonMap.entrySet()) {
                if (m.getKey().getName().equals(buttonName)) {
                    if (m.getValue() == player) {
                        diagonalConnect++;
                        winnerMap.put(m.getKey(), m.getValue());
                    }

                    // If counter has not reached 4 yet, reset counter when the next is not the correct value
                    else if (diagonalConnect < 4) {
                        diagonalConnect = 0;
                        winnerMap.clear();
                    }
                }
            }
        }

        if (diagonalConnect >= 4) return true;
        diagonalConnect = 0;

        // If there is no diagonal up connect four, check for diagonal down connect four

        // Check at what letter to start
        startLetter = 0;
        for (int i = 0; i < columnLetters.length; i++) {
            if (columnLetters[i].equals(column)) {
                startLetter = i;
            }
        }

        // Check at what number to start
        startNumber = row;

        // 'Go back' to the combination of the lowest letter + the highest number
        while (startLetter > 0 && startNumber < rows) {
            startLetter--;
            startNumber++;
        }

        for (int i = startLetter, j = startNumber; i < columnLetters.length; i++, j--) {
            if (j < 1) break;
            String buttonName = String.format("Button%s%d", columnLetters[i], j);
            // System.out.println(buttonName); // Debug print statement

            // Iterate over all buttons. Find the value of the button to compare to the player's value
            for (Map.Entry<JButton, Integer> m : buttonMap.entrySet()) {
                if (m.getKey().getName().equals(buttonName)) {
                    if (m.getValue() == player) {
                        diagonalConnect++;
                        winnerMap.put(m.getKey(), m.getValue());
                    }

                    // If counter has not reached 4 yet, reset counter when the next is not the correct value
                    else if (diagonalConnect < 4) {
                        diagonalConnect = 0;
                        winnerMap.clear();
                    }
                }
            }
        }

        if (diagonalConnect >= 4) return true;
        else return false;
    }

    private void clearGameBoard() {
        for (Map.Entry<JButton, Integer> m : buttonMap.entrySet()) {
            m.setValue(0);
            m.getKey().setLabel(" ");
            turn = GameState.PLAYER_1;
            updateTurnInfo();
            m.getKey().setBackground(Color.lightGray);
            winnerMap.clear();
        }
    }

    private void updateTurnInfo() {
        if (turn == GameState.PLAYER_1) {
            turnInfo.setText("Player 1's turn (X)");
        } else {
            turnInfo.setText("Player 2's turn (O)");
        }
    }

    private void updateTurnInfo(String text) {
        turnInfo.setText(text);
    }
}
