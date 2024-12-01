package mancala;

import java.util.ArrayList;
import java.util.Stack;

public class MancalaBoard {
    private ArrayList<MancalaListener> listenerList; //To add listeners for the DS.
    private int turn; //If turn is odd, then player is A. If turn is even, then player is B.
    private Stack<ArrayList<Integer>> undoFunction;
    private int selectedUndos; // how many times the player has already used the undo function
    private static final int MAX_UNDOS = 3;
    private ArrayList<Integer> board;

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
        undoFunction = new Stack<>();
        turn = 0;
        selectedUndos = 0;
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
    	turn++;
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
    public void notifyListener()
    {
    	for (MancalaListener listener : listenerList)
    	{
    		listener.notify();
    	}
    }
    
   /**
    * Nikki Huynh
    * Get the current state of the board
    * @return board ArrayList with the current state of the board.
    */
    public ArrayList<Integer> currentBoardState() {
        return new ArrayList<>(board); // gets current state of the board
    }

    /**
     * Nikki Huynh
     * Saves the current state of the board.
     */
    public void saveCurrentBoard() {
        undoFunction.push(new ArrayList<>(board)); // save the current state of the baord
        selectedUndos = 0; //user has not selected to undo yet
    }

    /**
     * Nikki Huynh
     * Allows user to undo their last action and restore to previous state, checks that the undo is valid.
     * @return true if undo function was successful.
     * @return false if undo function was not successful.
     */
    public boolean undo() {
        if(!undoFunction.isEmpty() && selectedUndos < MAX_UNDOS) {
            board.clear(); // clear board
            board.addAll(undoFunction.pop()); // get previous board state
            selectedUndos++;
            notifyListener(); // user has selected undo
            return true; // user was able to undo (did not reach max)
        }

        return false; // user was not able to undo (met max)
    }

    /**
     * Nikki Huynh
     * Gets the amount of times the user has selected to undo an action.
     * @return
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

    /**
     * Nikki Huynh
     * Updates the board after user has completed an action.
     * @param pitIndex - index the stones are being moved from.
     */
    public void updateBoard(int pitIndex) {
        int stones = board.get(pitIndex);
        board.set(pitIndex, 0);

        int currentIndex = pitIndex;
        while(stones > 0) {
            currentIndex = (currentIndex + 1) % 14; // to traverse index through board
            if((turn % 2 == 1 && currentIndex != 13) || (turn % 2 == 0 && currentIndex != 6)) {
                board.set(currentIndex, board.get(currentIndex) + 1);
                stones--;
            }
        }
    }
}

