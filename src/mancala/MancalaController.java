package mancala;

public class MancalaController {
    private MancalaBoard board;
    private MancalaView view;

    public MancalaController(MancalaBoard board, MancalaView view) {
        this.board = board;
        this.view = view;
        //will add listeners in the future to this, so that we can handle all the ations from the users
    }

    public void handlePitSelection(int pitIndex, boolean isPlayerA) {
        //update the model based on the selected pit
        //call view.refresh() to update the UI
    }
}
