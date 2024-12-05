
package mancala;

/**
 * Format2.java : Fall 2024 Dr. Kim's CS151 Team Project Solution.
 * @author Sarthak Sethi, Vincent Pangilinan, Nikki Huynh.
 * @version 1.0 12/4/2024
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Format2 implements FormatStrategy {
    private final JPanel panel;
    private final ArrayList<Pit> pits;
    private final MancalaBoard board;

    public Format2(MancalaBoard board) {
        this.board = board;
        this.panel = new JPanel();
        this.pits = new ArrayList<>(14);
        for (int i = 0; i < 14; i++) {
            pits.add(null);
        }
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(0, 0, 0)); // Board color
        initializeBoard();
    }

    /**
     * Sarthak Sethi
     * Sets up the format and style for the elements within the interface for the Mancala Board.
     * This is the second format, it may be changed if the player decieds to click on "Format2".
     * Sets up the format and the style and size of the pits and mancalas.
     */
    private void initializeBoard() {
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6, 10, 10));
        pitsPanel.setOpaque(false);
        pitsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Creates the  Mancalas
        Pit mancalaA = new Pit("Mancala A", board.currentBoardState().get(6), true, 6);
        Pit mancalaB = new Pit("Mancala B", board.currentBoardState().get(13), true, 13);
        pits.set(6, mancalaA);
        pits.set(13, mancalaB);

        mancalaA.setPreferredSize(new Dimension(100, 200));
        mancalaB.setPreferredSize(new Dimension(100, 200));

        panel.add(mancalaB, BorderLayout.WEST);
        panel.add(mancalaA, BorderLayout.EAST);

        // Add B's pits (top row)
        for (int i = 12; i >= 7; i--) {
            String label = "B" + (13 - i);
            Pit pit = new Pit(label, board.currentBoardState().get(i), false, i);
            pits.set(i, pit);
            pit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            pitsPanel.add(pit);
        }

        // Add A's pits (bottom row)
        for (int i = 0; i < 6; i++) {
            String label = "A" + (i + 1);
            Pit pit = new Pit(label, board.currentBoardState().get(i), false, i);
            pits.set(i, pit);
            pit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            pitsPanel.add(pit);
        }

        panel.add(pitsPanel, BorderLayout.CENTER);
    }

    /**
     * Sarthak Sethi
     * This returns the panel for the interface.
     * @return Jpanel panel for the layout and interface.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Sarthak Sethi
     * This returns the ArrayList of pits used within the interface for the mancala board.
     * @return Pit's pits object that contains the particular stones for the game.
     */
    public ArrayList<Pit> getPits() {
        return pits;
    }
}