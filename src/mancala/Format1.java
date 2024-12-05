package mancala;

/**
 * Format1.java : Fall 2024 Dr. Kim's CS151 Team Project Solution.
 * @author Sarthak Sethi, Vincent Pangilinan, Nikki Huynh.
 * @version 1.0 12/4/2024
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Concrete strategy class that implements abstract class (FormatStrategy).
 * Format 1 class sets up the format and style for the elements for the Mancala Board.
 * This is the default format, may be changed it player selects "Format2". 
 */
public class Format1 implements FormatStrategy {
    private final JPanel panel;
    private final ArrayList<Pit> pits;
    private final MancalaBoard board;

    /**
     * Nikki Huynh
     * Constructs the Format1 view for the Mancala game.
     * Initializes the mancala board's layout and colors, as well as setting the number of stones in the pit the player selected.
     * @param board - MancalaBoard board object for the model.
     */
    public Format1(MancalaBoard board) {
        this.board = board;
        this.panel = new JPanel();
        this.pits = new ArrayList<>(14);
        for(int i = 0; i < 14; i++) { 
            pits.add(null);
        }
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(184, 134, 11)); // Board color
        initializeBoard(board);
    }

    private void initializeBoard(MancalaBoard board) {
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6, 10, 10)); 
        pitsPanel.setOpaque(false);

        // Create Mancalas
        Pit mancalaA = createStyledPit("Mancala A", board.currentBoardState().get(6), true, 6); 
        Pit mancalaB = createStyledPit("Mancala B", board.currentBoardState().get(13), true, 13);
        pits.set(6, mancalaA);
        pits.set(13, mancalaB);

        mancalaA.setPreferredSize(new Dimension(100, 200));
        mancalaB.setPreferredSize(new Dimension(100, 200));  
        
        panel.add(mancalaB, BorderLayout.WEST);
        panel.add(mancalaA, BorderLayout.EAST);
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

    /**
     * Returns the panel for the interface.
     * @return Jpanel panel for the layout and interface.
     */
    public JPanel getPanel() {
        return panel;
    }
    
    /**
     * Returns the ArrayList of pits used for the mancala board.
     * @return Pit's pits object that contains the stones for the game.
     */
    public ArrayList<Pit> getPits() {
        return pits;
    }
}