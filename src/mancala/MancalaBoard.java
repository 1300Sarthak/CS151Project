package mancala;

import java.util.ArrayList;

public class MancalaBoard {
    private ArrayList<MancalaListener> listenerList; //To add listeners for the DS.
    private int turn; //If turn is odd, then player is B. If turn is even, then player is A.
    private ArrayList<Integer> previousBoard; // Store only the previous state
    private int previousTurn; // Store the previous turn
    private int selectedUndos; // how many times the player has already used the undo function
    private static final int MAX_UNDOS = 3;
    private ArrayList<Integer> board;
    private boolean lastMoveEndedInMancala;
    private boolean canUndo; // Track if undo is allowed

    public MancalaBoard(int initialStones) {
        //pits[0, 6] to be player A's mancala side, with 6 being Mancala A, and [7, 13] for player B, with 13 being Mancala B.
        board = new ArrayList<>(14);
        //pits = new int[14];
        for (int i = 0; i < 14; i++)
        {
            if(i != 6 && i != 13) {
                board.add(i, initialStones); // add initial stones to pits
            }
            else {
                board.add(0); // 0 stones for Mancala A and B
            }
        }
        
        listenerList = new ArrayList<>();
        turn = 0;
        selectedUndos = 0;
        lastMoveEndedInMancala = false;
        canUndo = false;
        previousBoard = null;
    }
    
    public ArrayList<Integer> getBoard()
    {
        return board;
    }

    /**
     * Vincent Pangilinan
     * Progresses the turn.
     */
    public void progressTurn()
    {
        if (!lastMoveEndedInMancala) {
            turn++;
            resetSelectedUndos();
            previousBoard = null; // Clear previous state
            canUndo = false; // Reset undo availability
            notifyListeners();
        }
        lastMoveEndedInMancala = false;
    }

    /**
     * Vincent Pangilinan
     * 
     * @return turn the turn count.
     */
    public int getTurnCount()
    {
        return turn;
    }
    
    /**
     * Vincent Pangilinan
     * Adding attach method for listeners.
     */
    public void attach(MancalaListener listener)
    {
        listenerList.add(listener);
    }
    
    /**
     * Vincent Pangilinan
     * Adding notifier methods for listeners.
     */
    private void notifyListeners()
    {
        for (MancalaListener listener : listenerList)
        {
            listener.changed();
        }
    }
    
    /**
    * Nikki Huynh
    * Get the current state of the board
    * @return board ArrayList with the current state of the board.
    */
    public ArrayList<Integer> currentBoardState() {
        return new ArrayList<>(board);
    }

    /**
     * Nikki Huynh
     * Saves the current state of the board.
     */
    private void saveCurrentBoard() {
        previousBoard = new ArrayList<>(board);
        previousTurn = turn;
        canUndo = true;
    }
    
    /**
     * Nikki Huynh
     * Allows user to undo their last action and restore to previous state, checks that the undo is valid.
     * @return true if undo function was successful.
     * @return false if undo function was not successful.
     */
    public boolean undo() {
        if (previousBoard != null && canUndo && selectedUndos < MAX_UNDOS) {
            board = new ArrayList<>(previousBoard);
            turn = previousTurn;
            selectedUndos++;
            canUndo = false; // Prevent multiple undos in a row
            notifyListeners();
            return true;
        }
        return false;
    }
    
    /**
     * Nikki Huynh
     * Updates the board after user has completed an action.
     * @param pitIndex - index the stones are being moved from.
     */
    public void updateBoard(int pitIndex) {
        if (!isValidMove(pitIndex)) {
            return;
        }

        saveCurrentBoard(); // Save state before making the move
        
        int stones = board.get(pitIndex);
        board.set(pitIndex, 0);
        
        int currentIndex = pitIndex;
        
        while (stones > 0) {
            currentIndex = (currentIndex + 1) % 14;
            
            if ((turn % 2 == 0 && currentIndex == 13) || (turn % 2 == 1 && currentIndex == 6)) {
                continue;
            }
            
            board.set(currentIndex, board.get(currentIndex) + 1);
            stones--;
        }
        
        lastMoveEndedInMancala = (turn % 2 == 0 && currentIndex == 6) || 
                                (turn % 2 == 1 && currentIndex == 13);
        
        if (!lastMoveEndedInMancala && board.get(currentIndex) == 1) {
            if ((turn % 2 == 0 && currentIndex < 6) || 
                (turn % 2 == 1 && currentIndex > 6 && currentIndex < 13)) {
                int oppositeIndex = 12 - currentIndex;
                if (board.get(oppositeIndex) > 0) {
                    int mancalaIndex = (turn % 2 == 0) ? 6 : 13;
                    board.set(mancalaIndex, 
                            board.get(mancalaIndex) + 
                            board.get(oppositeIndex) + 1);
                    board.set(oppositeIndex, 0);
                    board.set(currentIndex, 0);
                }
            }
        }
        
        notifyListeners();
    }

    private boolean isValidMove(int pitIndex) {
        if (board.get(pitIndex) == 0) {
            return false;
        }
        
        boolean isPlayerA = turn % 2 == 0;
        
        if (isPlayerA && (pitIndex >= 7)) {
            return false;
        }
        if (!isPlayerA && (pitIndex <= 5)) {
            return false;
        }
        
        return pitIndex != 6 && pitIndex != 13;
    }

    /**
     * Nikki Huynh
     * Gets the amount of times the user has selected to undo an action.
     * @return selectedUndos number of times undo has been used in current turn
     */
    public int getSelectedUndos() { 
        return selectedUndos;
    }

    /**
     * Nikki Huynh
     * Reset selected undo for each new turn.
     */
    public void resetSelectedUndos() {
        selectedUndos = 0;
    }

    public String getCurrentPlayer() {
        return (turn % 2 == 0) ? "Player A" : "Player B";
    }

    public boolean isGameOver() {
        boolean playerASideEmpty = true;
        boolean playerBSideEmpty = true;
        
        for (int i = 0; i < 6; i++) {
            if (board.get(i) > 0) {
                playerASideEmpty = false;
                break;
            }
        }
        
        for (int i = 7; i < 13; i++) {
            if (board.get(i) > 0) {
                playerBSideEmpty = false;
                break;
            }
        }

        if(playerASideEmpty || playerBSideEmpty) {
            getRemainingStones();
            notifyListeners();
            return true;
        }
        
        return false;
    }

    /**
     * Nikki Huynh
     * When the game is over, add remaining stones in the Players' pits to their resepective mancala.
     */
    private void getRemainingStones() {
        for(int i = 0; i < 6; i++) { //Player A
            board.set(6, board.get(6) + board.get(i));
            board.set(i, 0);
        }
        for(int i = 7; i < 13; i++) { //Player B
            board.set(13, board.get(13) + board.get(i));
            board.set(i, 0);
        }
    }
}