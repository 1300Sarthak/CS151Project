package mancala;

import java.util.ArrayList;
import java.util.Stack;

public class MancalaBoard {
    private int[] pits; //Changed to congregate pits into 1 array for one iterator.
    private ArrayList<MancalaListener> listenerList; //To add listeners for the DS.
    private int turn;
    private Stack<ArrayList<Integer>> undoFunction;
    private int selectedUndos; // how many times the player has already used the undo function
    private static final int MAX_UNDOS = 3;
    private final ArrayList<Integer> board;

    public MancalaBoard(int initialStones) {
    	//pits[0, 6] to be player A's mancala side, with 6 being Mancala A, and [7, 13] for player B, with 13 being Mancala B.
        board = new ArrayList<>(14);
    	//pits = new int[14];
        for (int i = 0; i < 14; i++)
        {
        	if(i != 6 && i != 13) {
        		pits[i] = initialStones; // add initial stones to pits
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
    
    /**
     * Vincent Pangilinan
     * Progresses the turn.
     */
    public void turn()
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
     * 
     * @return pits the underlying data structure.
     */
    public int[] getArray()
    {
    	return pits;
    }

    // will add more methods in the future to handle the game logic (moving /capturing stones)
    
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
    		listener.changed();
    	}
    }
    
    /**
     * Vincent Pangilinan
     * Adding Iterator method.
     */
    public MancalaIterator mancalaIterator()
    {
    	return new MancalaIterator(pits);
    }
    
    /**
     * Vincent Pangilinan
     * Move method for taking stones. Sets current index value to 0, distributes stones until out of stones. Congregates a move and deposit method.
     */
    public void move(int index)
    {
    	int taken = pits[index]; //temporary holder for distribution.
    	pits[index] = 0; 
    	for (int i = index + 1; i <= taken; i++) //start at the pit after the selected pit, continue until value is reached.
    	{
    		pits[i] += 1;
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
     * @param isPlayerA - checks if it is player A's turn, if not then it is Player B's turn.
     */
    public void updateBoard(int pitIndex, boolean isPlayerA) {
        int stones = board.get(pitIndex);
        board.set(pitIndex, 0);

        int currentIndex = pitIndex;
        while(stones > 0) {
            currentIndex = (currentIndex + 1) % 14; // to traverse index through board
            if((isPlayerA && currentIndex != 13) || (!isPlayerA && currentIndex != 6)) {
                board.set(currentIndex, board.get(currentIndex) + 1);
                stones--;
            }
        }
    }
}

