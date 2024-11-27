package mancala;
import javax.swing.*;
import java.awt.*;

<<<<<<< HEAD
public class MancalaView extends JPanel implements MancalaListener{
    private MancalaBoard board;
    private JButton undoButton;
    private JButton formatOne;
    private JButton formatTwo;
=======
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
>>>>>>> bdbcb1e3c9d99a0fbc5c4cefed8ded95ce76b677

/**
 * Nikki Huynh
 * Added GUI for Mancala game.
 * May still need to adjust to fit rest of code.
 */
public class MancalaView extends JFrame {
    private final Map<String, PitPanel> pitPanels;

    public MancalaView() {
        pitPanels = new HashMap<>();
        initializeGUI();
    }
    
    public JButton getUndoButton()
    {
    	return undoButton;
    }
    
    public JButton getFormatOneButton()
    {
    	return formatOne;
    }
    
    public JButton getFormatTwoButton()
    {
    	return formatTwo;
    }

    private void initializeGUI() {
        setTitle("Mancala Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(184, 134, 11)); 
                g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50);
            }
        };
        boardPanel.setLayout(new BorderLayout());
        boardPanel.setBackground(new Color(139, 69, 19));

        // Mancala B
        PitPanel mancalaB = new PitPanel("Mancala B", 0, true);
        pitPanels.put("Mancala B", mancalaB);
        boardPanel.add(mancalaB, BorderLayout.WEST);
        mancalaB.setPreferredSize(new Dimension(200, 300));

        // Mancala A
        PitPanel mancalaA = new PitPanel("Mancala A", 0, true);
        pitPanels.put("Mancala A", mancalaA);
        boardPanel.add(mancalaA, BorderLayout.EAST);
        mancalaA.setPreferredSize(new Dimension(200, 300));

        // Pits
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6)); // two rows
        pitsPanel.setOpaque(false);

        String[] topRowLabels = {"B6", "B5", "B4", "B3", "B2", "B1"};
        for (int i = 0; i < topRowLabels.length; i++) {
            String label = topRowLabels[i];
            PitPanel pit = new PitPanel(label, 4);
            pitPanels.put(label, pit);
            pit.setPreferredSize(new Dimension(60, 60));
            pitsPanel.add(pit);
        }

        String[] bottomRowLabels = {"A1", "A2", "A3", "A4", "A5", "A6"};
        for (int i = 0; i < bottomRowLabels.length; i++) {
            String label = bottomRowLabels[i];
            PitPanel pit = new PitPanel(label, 4);
            pitPanels.put(label, pit);
            pit.setPreferredSize(new Dimension(60, 60));
            pitsPanel.add(pit);
        }


        boardPanel.add(pitsPanel, BorderLayout.CENTER);
        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void updatePit(String pitName, int stoneCount) {
        PitPanel pit = pitPanels.get(pitName);
        if (pit != null) {
            pit.setStoneCount(stoneCount);
            pit.repaint();
        }
    }
<<<<<<< HEAD
}
=======

    private static class PitPanel extends JPanel {
        private final String pitName;
        private int stoneCount;
        private final boolean isMancala;

        public PitPanel(String pitName, int stoneCount) {
            this(pitName, stoneCount, false);
        }

        public PitPanel(String pitName, int stoneCount, boolean isMancala) {
            this.pitName = pitName;
            this.stoneCount = stoneCount;
            this.isMancala = isMancala;
            if (isMancala) {
                setPreferredSize(new Dimension(100, 300));
            } else {
                setPreferredSize(new Dimension(60, 60));
            }

            setBackground(new Color(211, 168, 118)); 
        }

        public void setStoneCount(int stoneCount) {
            this.stoneCount = stoneCount;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(new Color(126, 100, 70)); 
            if (isMancala) {
                g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50);
            } else {
                g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 30, 30);
            }

            // Stones
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

            // Draw pit name
            g2d.setColor(Color.WHITE);
            FontMetrics metrics = g2d.getFontMetrics();
            int labelWidth = metrics.stringWidth(pitName);
            int xLabel = (getWidth() - labelWidth) / 2;  // x-coordinate for text
            int yLabel = getHeight() - 10;  // y-coordinate for text

            g2d.drawString(pitName, xLabel, yLabel);  // label at bottom center of pits
        }
    }
}
>>>>>>> bdbcb1e3c9d99a0fbc5c4cefed8ded95ce76b677
