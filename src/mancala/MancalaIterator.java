package mancala;

public class MancalaIterator implements IteratorInterface {
    private int[] pits;
    private int index;

    /**
     * 
     * @param pits the model.
     */
    public MancalaIterator(int[] pits) {
        this.pits = pits;
    }
    
    /**
     * Pit selector method.
     * @param index
     */
    public void selectPit(int index)
    {
    	this.index = index;
    }

    @Override
    public boolean hasNext() {
        return index < pits.length;
    }

    @Override
    public int next() {
        return pits[index++];
    }
}

