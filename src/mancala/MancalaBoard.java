package mancala;
import java.util.*;

<<<<<<< HEAD
public class MancalaBoard {
	private int[] board;
	private int turn;
	
	/**
	 * Underlying data structure for MancalaBoard. Using array for quick indexing/population.
	 * i = 6 contains player 1's Mancala, i = 13 contains player 2's mancala
	 * 
	 */
	public MancalaBoard()
	{
		board = new int[14];
		turn = 0;
	}
	
	/**
	 * Fills the mancala pits with the specified number of marbles. Marbles must be 0 < marbles <= 4
	 * @param marbles
	 */
	public void fill(int marbles)
	{
		if (marbles < 0 || marbles > 4)
		{
			System.out.println("Incompatible marble count");
			return;
		}
		else
		{
			for (int i = 0; i < board.length; i++)
			{
				if (i == 6 || i == 13)
				{
					board[i] = 0;
				}
				else
				{
					board[i] = marbles;
				}
			}
		}
	}
	
	public void take()
	{
		
	}
	
	public void deposit()
	{
		
	}
}
=======
import java.util.ArrayList;
import java.util.Stack;

public class MancalaBoard {
    private int[] pitsA; //pits for player A
    private int[] pitsB; //pits for player B
    private int mancalaA; //mncala for player A
    private int mancalaB; //mancala for player B

    public MancalaBoard(int initialStones) {
        pitsA = new int[6];
        pitsB = new int[6];
        for (int i = 0; i < 6; i++) {
            pitsA[i] = initialStones;
            pitsB[i] = initialStones;
        }
        mancalaA = 0;
        mancalaB = 0;
    }

    // will add more methods in the future to handle the game logic (moving /capturing stones)

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
>>>>>>> a3a722b512006b3b273fbfe6b6d5fc60bd059059
