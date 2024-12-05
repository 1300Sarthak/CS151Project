package mancala;

/**
 * MancalaView.java : Fall 2024 Dr. Kim's CS151 Team Project Solution.
 * @author Sarthak Sethi, Vincent Pangilinan, Nikki Huynh.
 * @version 1.0 12/4/2024
 */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Nikki Huynh
 * MancalaView class is the graphical user interface of the Mancala program. 
 * Displays the Mancala board, the pits, mancalas, and buttons for user preference.
 * The class listens for Player movements and updates the board to match the result of their movements.
 */
public class MancalaView extends JFrame implements MancalaListener {
    private final JPanel viewPanel;
    private final MancalaBoard board;
    private final ArrayList<Pit> pits;
    private JButton undoButton;
    private JButton nextTurnButton;
    private MancalaController controller;
    private FormatStrategy currentFormat;

    /**
     * Nikki Huynh
     * Constructs the MancalaView object with the board and proper formatting.
     * 
     * @param board - the MancalaBoard object to represent the current board
     * @param format - layout of the format the player selected
     */
    public MancalaView(MancalaBoard board, String format) {
        this.board = board;
        this.pits = new ArrayList<>(14);
        this.viewPanel = new JPanel(new BorderLayout());
        
        undoButton = new JButton("Undo");
        nextTurnButton = new JButton("Next Turn");
        
        switchFormat(format);
        board.attach(this);
    }
    
    /**
     * Nikki Huynh
     * Sets controller in charge of handling the player's input and the logic of the game.
     * 
     * @param controller - MancalaController object controller
     */
    public void setController(MancalaController controller) {
        this.controller = controller;
    }
    
    /**
     * Nikki Huynh
     * Changes the stones in the pits after player has made a move.
     * Only called when the board has been updated or changed.
     */
    @Override
    public void changed() {
        for (int i = 0; i < pits.size(); i++) {
            Pit pit = pits.get(i);
            if (pit != null) {
                pit.setStoneCount(board.currentBoardState().get(i));
            }
        }
        repaint();
    }
    
    /**
     * Nikki Huynh
     * Returns the undo button.
     * @return undo button
     */
    public JButton getUndoButton() {
        return undoButton;
    }
    
    /**
     * Nikki Huynh
     * Returns the next turn button.
     * @return next turn button
     */
    public JButton getNextTurnButton() {
        return nextTurnButton;
    }
    
    /**
     * Sarthak Sethi
     * Returns the ArrayList of pits from the view.
     * @return list of Pit objects
     */
    public ArrayList<Pit> getPitList() {
        return pits;
    }
    
    /**
     * Vincent Pangilinan
     * Returns the view panel.
     * @return view panel
     */
    public JPanel getPanel() {
        return viewPanel;
    }
    
    /**
     * Vincent Pangilinan
     * Context for the strategy pattern. Updates the style of the board.
     * @param format the name of the formatting pattern.
     */
    public void switchFormat(String format) {
        viewPanel.removeAll();
        FormatStrategy newFormat;
        
        if ("Format1".equalsIgnoreCase(format)) {
            newFormat = new Format1(board);
        } else if ("Format2".equalsIgnoreCase(format)) {
            newFormat = new Format2(board);
        } else {
            throw new IllegalArgumentException("Invalid format: " + format);
        }
        
        currentFormat = newFormat;
        viewPanel.add(newFormat.getPanel(), BorderLayout.CENTER);
        
        // Update pits list
        pits.clear();
        if (newFormat instanceof Format1) {
            pits.addAll(((Format1) newFormat).getPits());
        } else if (newFormat instanceof Format2) {
            pits.addAll(((Format2) newFormat).getPits());
        }
        
        if (controller != null) {
            controller.resetPitListeners();
        }
        
        viewPanel.revalidate();
        viewPanel.repaint();
    }
}