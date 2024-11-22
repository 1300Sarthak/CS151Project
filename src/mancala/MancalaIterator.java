package mancala;

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
}

