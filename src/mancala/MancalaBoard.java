package mancala;

import java.util.ArrayList;
import java.util.Stack;

public class MancalaBoard {
    private ArrayList<MancalaListener> listenerList; //To add listeners for the DS.
    private int turn; //If turn is odd, then player is B. If turn is even, then player is A.
    //private Stack<ArrayList<Integer>> undoStack;
    private SingleStack<ArrayList<Integer>> uStack; //Added by Vincent.
    private Stack<Integer> turnStack;  // Added to track turns for undo
    private int selectedUndos; // how many times the player has already used the undo function
    private static final int MAX_UNDOS = 3;
    private ArrayList<Integer> board;
    private boolean lastMoveEndedInMancala;

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
        //undoStack = new Stack<>();
        uStack = new SingleStack<ArrayList<Integer>>();
        turnStack = new Stack<>();
        turn = 0;
        selectedUndos = 0;
        lastMoveEndedInMancala = false;
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
            //selectedUndos = 0;
            resetSelectedUndos();
            //undoStack.clear(); // Clear undo stack for new turn
            uStack.clear();
            turnStack.clear(); // Clear turn stack as well
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
    public void saveCurrentBoard() {
        //undoStack.push(new ArrayList<>(board));// save the current state of the baord
        uStack.push(new ArrayList<>(board));
        turnStack.push(turn);
        //selectedUndos = 0;//user has not selected to undo yet
    }


    public boolean undo() {
        //if (!undoStack.isEmpty() && selectedUndos <= MAX_UNDOS) //Changed < to <= to have 3 undos.
    	if (!uStack.isEmpty() && selectedUndos <= MAX_UNDOS)
        {
            //board = new ArrayList<>(undoStack.pop());
    		board = new ArrayList<>(uStack.pop());
            if (!turnStack.isEmpty()) {
                turn = turnStack.pop();
            }
            selectedUndos++;
            notifyListeners();
            return true;
        }
        else {
            progressTurn();
        }
        return false;
    }
    
     /**
     * Nikki Huynh
     * Updates the board after user has completed an action.
     * @param pitIndex - index the stones are being moved from.
     */
    public void updateBoard(int pitIndex) {
        saveCurrentBoard();
        
        if (!isValidMove(pitIndex)) {
            //if (!undoStack.isEmpty()) {
        	if (!uStack.isEmpty()) {
                //undoStack.pop();
        		uStack.pop();
                turnStack.pop();
            }
            return;
        }

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
        
        if (!lastMoveEndedInMancala) {
            turn++;
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
        
        return playerASideEmpty || playerBSideEmpty;
    }
    
    private final class SingleStack<E> {
    	private Stack<E> stack;
    	private final int size = 1;
    	
    	private SingleStack()
    	{
    		stack = new Stack<>();
    	}
    	
    	private void push(E move)
    	{
    		if (stack.size() >= size)
    		{
    			stack.clear();
    		}
    			stack.push(move);
    	}
    	
    	public E pop() 
    	{
    		if (stack.isEmpty()) return null;
    		else
    		{
    			return stack.pop();
    		}
    	}
    	
    	public void clear()
    	{
    		stack.clear();
    	}
    	
    	public boolean isEmpty()
    	{
    		if (stack != null) return false;
    		else
    			return true;
    	}
    }
}