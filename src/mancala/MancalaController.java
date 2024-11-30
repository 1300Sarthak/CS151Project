package mancala;
import java.awt.event.*;

public class MancalaController {
    private MancalaBoard board;
    private MancalaView view;

    public MancalaController(MancalaBoard board, MancalaView view) {
        this.board = board;
        this.view = view;
        
        view.getUndoButton().addActionListener(event -> undo());
        view.getNextTurnButton().addActionListener(event -> {
        														board.progressTurn();
        														board.resetSelectedUndos();
        													});
        view.addMouseListener(new PitClicker());
        //will add listeners in the future to this, so that we can handle all the ations from the users
    }

    public void handlePitSelection(int pitIndex, boolean isPlayerA) {
        //update the model based on the selected pit
        //call view.refresh() to update the UI
    }
    
    /**
     * Method to connect undo method in model to action in controller.
     */
    private void undo()
    {
    	board.undo();
    	view.changed();
    }
    
    private class PitClicker extends MouseAdapter
    {
    	public void clickPit(MouseEvent event)
    	{
    		
    	}
    }
}
