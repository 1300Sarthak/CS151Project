package mancala;

public class MancalaIterator implements IteratorInterface {
    private int[] pits;
    private int index;

    /**
     * 
     * @param pits the model.
     * @param selectedIndex VP: Changed to set index to equal selectedIndex, the selectedIndex is the
     * index clicked on by the mouse.
     */
    public MancalaIterator(int[] pits, int selectedIndex) {
        this.pits = pits;
        index = selectedIndex; //This is so we can start the iterator at the selected pit.
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

