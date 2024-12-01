package mancala;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Format2 extends FormatStrategy {
    private final JPanel panel;
    private final ArrayList<Pit> pits;

    public Format2(MancalaBoard board) {
        super(board);
        this.panel = new JPanel();
        this.pits = new ArrayList<>(14);
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(50, 205, 50)); // Board color
        initializeBoard(board);
    }

    private void initializeBoard(MancalaBoard board) {
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6));
        pitsPanel.setOpaque(false);

        Pit mancalaA = createPit("Mancala A", board.currentBoardState().get(6), true);
        Pit mancalaB = createPit("Mancala B", board.currentBoardState().get(13), true);
        pits.add(mancalaA);
        pits.add(mancalaB);

        panel.add(mancalaB, BorderLayout.WEST);
        panel.add(mancalaA, BorderLayout.EAST);

        String[] topRow = {"B6", "B5", "B4", "B3", "B2", "B1"};
        String[] bottomRow = {"A1", "A2", "A3", "A4", "A5", "A6"};

        for (String label : topRow) {
            Pit pit = createPit(label, board.currentBoardState().get(getPitIndex(label)), false);
            pits.add(pit);
            pitsPanel.add(pit);
        }

        for (String label : bottomRow) {
            Pit pit = createPit(label, board.currentBoardState().get(getPitIndex(label)), false);
            pits.add(pit);
            pitsPanel.add(pit);
        }

        panel.add(pitsPanel, BorderLayout.CENTER);
    }

    private Pit createPit(String name, int stones, boolean isMancala) {
        return new Pit(name, stones, isMancala);
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
            default: return -1; // Invalid label
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    private class Pit extends JPanel {
        private final String pitName;
        private int stoneCount;
        private final boolean isMancala;

        public Pit(String pitName, int stoneCount, boolean isMancala) {
            this.pitName = pitName;
            this.stoneCount = stoneCount;
            this.isMancala = isMancala;
            setOpaque(false);
            if (isMancala) {
                setPreferredSize(new Dimension(100, 300));
            } else {
                setPreferredSize(new Dimension(80, 80));
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(new Color(126, 100, 70)); // pit color
            if (isMancala) {
                g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50); 
            } else {
                g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20); 
            }

            g2d.setColor(Color.BLACK);
            int x = 20;
            int y = 20;
            int stoneDiameter = 25;
            for (int i = 0; i < stoneCount; i++) {
                g2d.fillOval(x, y, stoneDiameter, stoneDiameter);
                x += stoneDiameter + 5; 
                if (x + stoneDiameter > getWidth() - 20) {
                    x = 20; 
                    y += stoneDiameter + 5; 
                }
            }

            g2d.setColor(Color.WHITE);
            FontMetrics metrics = g2d.getFontMetrics();
            int labelWidth = metrics.stringWidth(pitName);
            int xLabel = (getWidth() - labelWidth) / 2; // center text
            int yLabel = getHeight() - 10; 
            g2d.drawString(pitName, xLabel, yLabel); // draw pit name
        }
    }
}

