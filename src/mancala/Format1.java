package mancala;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Format1 implements FormatStrategy {
    private final JPanel panel;
    private final ArrayList<Pit> pits;
    private final MancalaBoard board;

    public Format1(MancalaBoard board) {
        //super(board);
        this.board = board;
        this.panel = new JPanel();
        this.pits = new ArrayList<>(14);
        for(int i = 0; i < 14; i++) { //edited by Nikki Huynh
            pits.add(null);
        } //end of edit
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(184, 134, 11)); // Board color
        initializeBoard(board);
    }
    /* 
    private Pit createPit(String name, int stones, boolean isMancala, int index) {
        return new Pit(name, stones, isMancala, index);
    }

    private int getPitIndex(String label) {
        switch (label) {
            case "A1": return 0;
            case "A2": return 1;
            case "A3": return 2;
            case "A4": return 3;
            case "A5": return 4;
            case "A6": return 5;
            case "B1": return 7;
            case "B2": return 8;
            case "B3": return 9;
            case "B4": return 10;
            case "B5": return 11;
            case "B6": return 12;
            default: return -1;  // Invalid label
        }
    }

    public JPanel getPanel() {
        return panel;
    }
    */

    private void initializeBoard(MancalaBoard board) {
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6, 10, 10)); //edited and added (, 10, 10)
        pitsPanel.setOpaque(false);

        // Create Mancalas
        Pit mancalaA = createStyledPit("Mancala A", board.currentBoardState().get(6), true, 6); //edited
        Pit mancalaB = createStyledPit("Mancala B", board.currentBoardState().get(13), true, 13);
        //pits.add(mancalaA);
        //pits.add(mancalaB);
        pits.set(6, mancalaA);
        pits.set(13, mancalaB);

        mancalaA.setPreferredSize(new Dimension(100, 200));
        mancalaB.setPreferredSize(new Dimension(100, 200)); //edit ends 
        
        panel.add(mancalaB, BorderLayout.WEST);
        panel.add(mancalaA, BorderLayout.EAST);
        //edit
        // Add player B's pits 
        for(int i = 12; i >= 7; i--) {
            String label = "B" + (13 - i);
            Pit pit = createStyledPit(label, board.currentBoardState().get(i), false, i);
            pits.set(i, pit);
            pitsPanel.add(pit);
        }

        //Add player A's pits
        for(int i = 0; i < 6; i++) {
            String label = "A" + (i + 1);
            Pit pit = createStyledPit(label, board.currentBoardState().get(i), false, i);
            pits.set(i, pit);
            pitsPanel.add(pit);
        }

        panel.add(pitsPanel, BorderLayout.CENTER);
    }

    /**
     * Nikki Huynh
     * Sets the style and size of the pits and mancalas.
     */
    private Pit createStyledPit(String name, int stones, boolean isMancala, int index) {
        Pit pit = new Pit(name, stones, isMancala, index);
        pit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        if(!isMancala) {
            pit.setBackground(new Color(126, 100, 70));
            pit.setPreferredSize(new Dimension(100, 300));
        }
        else {
            pit.setBackground(new Color(126, 100, 70));
            pit.setPreferredSize(new Dimension(80, 80));
        }
        return pit;
    }

    public JPanel getPanel() {
        return panel;
    }
        /*
        String[] topRow = {"B6", "B5", "B4", "B3", "B2", "B1"};
        String[] bottomRow = {"A1", "A2", "A3", "A4", "A5", "A6"};
        
        // Add B's pits (top row)
        for (String label : topRow) {
            int index = getPitIndex(label);
            Pit pit = createPit(label, board.currentBoardState().get(index), false, index);
            pits.add(pit);
            pitsPanel.add(pit);
        }

        // Add A's pits (bottom row)
        for (String label : bottomRow) {
            int index = getPitIndex(label);
            Pit pit = createPit(label, board.currentBoardState().get(index), false, index);
            pits.add(pit);
            pitsPanel.add(pit);
        }

        panel.add(pitsPanel, BorderLayout.CENTER);
    }
    */

    public ArrayList<Pit> getPits() {
        return pits;



    // private class Pit extends JPanel {
    //     private final String pitName;
    //     private int stoneCount;
    //     private final boolean isMancala;

    //     public Pit(String pitName, int stoneCount, boolean isMancala) {
    //         this.pitName = pitName;
    //         this.stoneCount = stoneCount;
    //         this.isMancala = isMancala;
    //         setOpaque(false);
    //         if (isMancala) {
    //             setPreferredSize(new Dimension(100, 300));
    //         } else {
    //             setPreferredSize(new Dimension(80, 80));
    //         }
    //     }

    //     protected void paintComponent(Graphics g) {
    //         super.paintComponent(g);
    //         Graphics2D g2d = (Graphics2D) g;

    //         g2d.setColor(new Color(126, 100, 70)); // pit color
    //         if (isMancala) {
    //             g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50); 
    //         } else {
    //             g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20); 
    //         }

    //         g2d.setColor(Color.BLACK);
    //         int x = 20;
    //         int y = 20;
    //         int stoneDiameter = 25;
    //         for (int i = 0; i < stoneCount; i++) {
    //             g2d.fillOval(x, y, stoneDiameter, stoneDiameter);
    //             x += stoneDiameter + 5; 
    //             if (x + stoneDiameter > getWidth() - 20) {
    //                 x = 20; 
    //                 y += stoneDiameter + 5; 
    //             }
    //         }

    //         g2d.setColor(Color.WHITE);
    //         FontMetrics metrics = g2d.getFontMetrics();
    //         int labelWidth = metrics.stringWidth(pitName);
    //         int xLabel = (getWidth() - labelWidth) / 2; // center text
    //         int yLabel = getHeight() - 10; 
    //         g2d.drawString(pitName, xLabel, yLabel); // draw pit name
    //     }





    }
}