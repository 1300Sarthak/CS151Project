package mancala;
import java.awt.event.*;
import javax.swing.*;

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
        
        for (Pit pit : view.getPitList())
        {
        	if (pit != null)
        	{
        		pit.addMouseListener(new PitClicker());
        	}
        }
        //will add listeners in the future to this, so that we can handle all the ations from the users
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
    	private void clickPit(int pitIndex)
    	{
    		board.updateBoard(pitIndex);
    		view.changed();
    	}
    }
}
