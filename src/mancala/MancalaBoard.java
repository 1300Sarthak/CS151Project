package mancala;

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
}