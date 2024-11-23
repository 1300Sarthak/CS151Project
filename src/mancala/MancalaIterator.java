package mancala;

<<<<<<< HEAD
public class MancalaIterator implements IteratorInterface{

	private int[] board;
	private int stones = 0;
	/**
	 * Iterator for array traversal.
	 * @param board is the Mancala board to traverse
	 * @param stones is the stones at a pit, which will be picked up using take().
	 */
	public MancalaIterator(int[] board, int stones)
	{
		this.board = board;
		this.stones = stones;
	}
	
	/**
	 * Checks whether it is possible for the iterator to continue distributing stones.
	 */
	public boolean hasNext()
	{
		return stones > 0;
	}
	
	/**
	 * Distributes stones as long as number of stones > 0.
	 * 
	 * Might need to figure out a way to keep track of which pit the stones was taken out of.
	 * @param stones
	 * @return
	 */
	public Integer next(int stones)
	{
		while (hasNext())
		{
			board[]
		}
	}
=======
public class MancalaIterator implements IteratorInterface {
    private int[] pits;
    private int index;

    public MancalaIterator(int[] pits) {
        this.pits = pits;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < pits.length;
    }

    @Override
    public int next() {
        return pits[index++];
    }
>>>>>>> a3a722b512006b3b273fbfe6b6d5fc60bd059059
}

