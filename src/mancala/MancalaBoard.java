package mancala;
import java.util.*;

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
