package mancala;

/**
 * MancalaController.java : Fall 2024 Dr. Kim's CS151 Team Project Solution.
 * @author Sarthak Sethi, Vincent Pangilinan, Nikki Huynh.
 * @version 1.0 12/4/2024
 */

import java.awt.event.*;
import javax.swing.*;

public class MancalaController {
    private final MancalaBoard board;
    private final MancalaView view;

    public MancalaController(MancalaBoard board, MancalaView view) {
        this.board = board;
        this.view = view;
        view.setController(this);
        
        // Add listeners to buttons
        view.getUndoButton().addActionListener(e -> handleUndo());
        view.getNextTurnButton().addActionListener(e -> handleNextTurn());
        
        // Add listeners to pits
        resetPitListeners();
    }
    
    /**
    * Sarthak Sethi
    * Resets mouse listeners on all pits
    * by removing existing ones and adding new listeners to non-Mancala pits.
    */
    public void resetPitListeners() {
        // Remove existing listeners from all pits
        for (Pit pit : view.getPitList()) {
            if (pit != null) {
                for (MouseListener listener : pit.getMouseListeners()) {
                    pit.removeMouseListener(listener);
                }
            }
        }
        
        // Add new listeners to all non-Mancala pits
        for (Pit pit : view.getPitList()) {
            if (pit != null && !isMancalaPit(pit)) {
                pit.addMouseListener(new PitClickListener(pit));
            }
        }
    }
    
    /**
     * Sarthak Sethi
     * Handles undo action when clicked.
     * Updates the view if successful or shows a warning if no undos are left for the turn.
     */
    private void handleUndo() {
        if (board.undo()) {
            view.changed();
        } else {
            JOptionPane.showMessageDialog(null, 
                "No more undos available for this turn!", 
                "Undo Limit Reached", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Sarthak Sethi
     * Handles next turn action when clicked.
     * Updates the board and resets the undo count for the new turn.
     * Updates the view.
     */
    private void handleNextTurn() {
        board.progressTurn();
        board.resetSelectedUndos();
        view.changed();
    }
    
    /**
     * Sarthak Sethi
     * 
     * Checks if the given pit is a Mancala pit or not.
     * A Mancala pit is the storage pit on either side of the board.
     * 
     * @param pit the pit to check.
     * @return true if the pit is a Mancala pit, otherwise false.
     */
    private boolean isMancalaPit(Pit pit) {
        return pit.getIndex() == 6 || pit.getIndex() == 13;
    }

    private class PitClickListener extends MouseAdapter {
        private final Pit pit;

        public PitClickListener(Pit pit) {
            this.pit = pit;
        }

     /**
      * Nikki Huynh
      * Handles pit click events. Updates the board and view if the move is valid, and
      * checks for game over conditions.
      */
        @Override
        public void mouseClicked(MouseEvent e) {
            int pitIndex = pit.getIndex();
            
            // Check if it's a valid move
            if (isValidMove(pitIndex)) {
                board.updateBoard(pitIndex);
                view.changed();
                
                // Check for game over
                if (board.isGameOver()) {
                    handleGameOver();
                }
            }
        }
        
        
        /**
         * 
         * Vincent Pangilinan
         * Checks if a given move is valid. A move is invalid if
         * the pit is empty, or if it's not the current player's turn.
         * Shows an error message if the move is invalid.
         * 
         * 
         * @param pitIndex the index of the pit to check.
         * @return true if the move is valid, otherwise false.
         */
        private boolean isValidMove(int pitIndex) {
            // Check if pit has stones
            if (pit.getStoneCount() == 0) {
                JOptionPane.showMessageDialog(null, 
                    "This pit is empty!", 
                    "Invalid Move", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Check if it's the correct player's turn
            boolean isPlayerA = board.getTurnCount() % 2 == 0;
            if (isPlayerA && pitIndex > 5) {
                JOptionPane.showMessageDialog(null, 
                    "It's Player A's turn! Please select from the bottom row.", 
                    "Wrong Turn", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (!isPlayerA && pitIndex < 7) {
                JOptionPane.showMessageDialog(null, 
                    "It's Player B's turn! Please select from the top row.", 
                    "Wrong Turn", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            return true;
        }
        
        /**
         * Sarthak Sethi
         * 
        * Handles the end-of-game logic
        * Calculates final scores and displays the results in a JOptionPane and asks if players want to start a new game.
        * Starts a new game if agreed or closes the game otherwise.
         */
        private void handleGameOver() {
            board.isGameOver();
            // Calculate final scores
            int playerAScore = board.currentBoardState().get(6);
            int playerBScore = board.currentBoardState().get(13);
            
            String winner;
            if (playerAScore > playerBScore) {
                winner = "Player A wins!";
            } else if (playerBScore > playerAScore) {
                winner = "Player B wins!";
            } else {
                winner = "It's a tie!";
            }
            
            String message = String.format(
                "Game Over!\n\nFinal Scores:\nPlayer A: %d\nPlayer B: %d\n\n%s", 
                playerAScore, 
                playerBScore, 
                winner
            );
            
            JOptionPane.showMessageDialog(null,
                message,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Ask if players want to start a new game
            int response = JOptionPane.showConfirmDialog(null,
                "Would you like to start a new game?",
                "New Game",
                JOptionPane.YES_NO_OPTION);
                
            if (response == JOptionPane.YES_OPTION) {
                // Start new game
                String stonesInput = JOptionPane.showInputDialog(
                    "Enter the number of stones per pit (3 or 4):");
                try {
                    int stones = Integer.parseInt(stonesInput);
                    if (stones != 3 && stones != 4) {
                        stones = 3; // Default to 3 if invalid input
                    }
                    new MancalaGUI();
                } catch (NumberFormatException ex) {
                    // If input is invalid, start new game with 3 stones
                    new MancalaGUI();
                }
            } else {
                System.exit(0); // Close the game if players don't want to continue
            }
        }
    }

    /**
     * Sarthak Sethi 
     * 
     * Handle undo action.
     * 
     * This method will be called when the player hits the undo button.
     * It will call the handleUndo method to update the game state.
     */
    public void undo() {
        handleUndo();
    }
}