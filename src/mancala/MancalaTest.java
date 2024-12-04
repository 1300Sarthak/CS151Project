package mancala;

public class MancalaTest {

	public static void main(String[] args) {
		
		MancalaBoard board = new MancalaBoard(4);
		MancalaView view = new MancalaView(board, "Format1");
		new MancalaController(board, view);

	}

}
