package mancala;

public interface IteratorInterface {

	//Checks if there are more stones to distribute.
	boolean hasNext();
	
	//Will move to the next mancala pit.
	Integer next();
}
