import java.util.ArrayList;
import java.util.Stack;

public class MancalaBoard {
    private int[] pits; //Changed to congregate pits into 1 array for one iterator.
    private ArrayList<MancalaListener> listenerList; //To add listeners for the DS.
    private int turn;

    public MancalaBoard(int initialStones) {
    	//pits[0, 6] to be player A's mancala side, with 6 being Mancala A, and [7, 13] for player B, with 13 being Mancala B.
        pits = new int[14];
        for (int i = 0; i < pits.length; i++)
        {
        	pits[i] = initialStones;
        }
        
        listenerList = new ArrayList<MancalaListener>();
        turn = 0;
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
    	return new MancalaIterator(pits, 0);
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
     * Attempt for undo function using ArrayList and Stack.
     */
    private final ArrayList<Integer> board;
    private final Stack<ArrayList<Integer>> undoFunction; 
    private final int maxUndo = 3;
    private int selectedUndos; // how many times the player has already used the undo function
    
    // set up the board; will update once we have finalized the board setup; using ArrayList might be ideal

    public ArrayList<Integer> currentBoardState() {
        return new ArrayList<>(board); // gets current state of the board
    }

    public void saveCurrentBoard() {
        undoStack.push(new ArrayList<>(board)); // save the current state of the baord
        selectedUndos = 0; //user has not selected to undo yet
    }

    public boolean undo() {
        if(!undoFunction.isEmpty() && undoCount < maxUndo) {
            ArrayList<Integer> previousBoardState = undoStack.pop();
            for(int i = 0; i < board.size(); i++) {
                board.set(i, previousBoardState.get(i));
            }

            selectedUndos++; // user has selected undo
            return true; // user was able to undo (did not reach max)
        }

        return false; // user was not able to undo (met max)
    }

    public int getSelectedUndos() { // get the count of times user has selected undo
        return selectedUndos;
    }

    public void resetSelectedUndos() { // reset the count of times user has selected undo per new turn
        selectedUndos = 0; 
    }

    // still need to work on updating board after user has selected undo ... 
}

