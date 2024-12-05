
package mancala;

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

    private void initializeBoard() {
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6, 10, 10));
        pitsPanel.setOpaque(false);
        pitsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create Mancalas
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

    public JPanel getPanel() {
        return panel;
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }
}