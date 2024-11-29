package mancala;
import java.util.ArrayList;

public class MancalaIterator implements IteratorInterface {
    private ArrayList<Integer> pits;
    private int index;

    /**
     * 
     * @param pits the model.
     */
    public MancalaIterator(ArrayList<Integer> pits) {
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
        return index < pits.size();
    }

    @Override
    public int next() {
        return pits.get(index++);
    }
}

