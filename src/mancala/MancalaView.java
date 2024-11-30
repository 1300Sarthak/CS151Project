package mancala;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MancalaView {
    private final JPanel viewPanel; 
    private final MancalaBoard board;
    private final ArrayList<Pit> pits;

    public MancalaView(MancalaBoard board, String format) {
        this.board = board;
        pits = new ArrayList<>(14); 
        viewPanel = new JPanel(new BorderLayout());
        if ("Format1".equalsIgnoreCase(format)) {
            viewPanel.add(new Format1(board).getPanel(), BorderLayout.CENTER);
        } else if ("Format2".equalsIgnoreCase(format)) {
            viewPanel.add(new Format2(board).getPanel(), BorderLayout.CENTER);
        } else {
            throw new IllegalArgumentException("Invalid format");
        }
    }

    public JPanel getPanel() {
        return viewPanel;
    }
    
    public void switchFormat(String format) {
        viewPanel.removeAll();
        if ("Format1".equalsIgnoreCase(format)) {
            viewPanel.add(new Format1(board).getPanel(), BorderLayout.CENTER);
        } else if ("Format2".equalsIgnoreCase(format)) {
            viewPanel.add(new Format2(board).getPanel(), BorderLayout.CENTER);
        } else {
            throw new IllegalArgumentException("Invalid format");
        }
        viewPanel.revalidate();
        viewPanel.repaint();
    }

    private class Format1 {
        private final JPanel panel;

        public Format1(MancalaBoard board) {
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(184, 134, 11)); // Board color
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
                default: return -1; 
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
                g2d.drawString(pitName, xLabel, yLabel); // pit name
            }
        }
    }

    private class Format2 {
        private final JPanel panel;

        public Format2(MancalaBoard board) {
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(50, 205, 50)); 
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

            // top and bottom rows
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

                // Draw pit
                g2d.setColor(new Color(126, 100, 70)); // pit color
                if (isMancala) {
                    g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50); 
                } else {
                    g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20); 
                }

                // stones
                g2d.setColor(Color.BLACK);
                int x = 20;
                int y = 20;
                int stoneDiameter = 25;
                for (int i = 0; i < stoneCount; i++) {
                    g2d.fillOval(x, y, stoneDiameter, stoneDiameter);
                    x += stoneDiameter + 5; // Add space between stones
                    if (x + stoneDiameter > getWidth() - 20) {
                        x = 20; // Reset x position for next row of stones
                        y += stoneDiameter + 5; // Move down to the next row
                    }
                }

                // Draw pit name
                g2d.setColor(Color.WHITE);
                FontMetrics metrics = g2d.getFontMetrics();
                int labelWidth = metrics.stringWidth(pitName);
                int xLabel = (getWidth() - labelWidth) / 2; // Center  text
                int yLabel = getHeight() - 10;
                g2d.drawString(pitName, xLabel, yLabel); // Draw pit name
            }
        }
    }
}
