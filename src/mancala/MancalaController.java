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
        
        for (Pit pit : view.getPitList())
        {
        	if (pit != null)
        	{
        		pit.addMouseListener(new PitClicker(pit));
        	}
        }
        //will add listeners in the future to this, so that we can handle all the ations from the users
    }
    
    /**
     * Method to connect undo method in model to action in controller.
     */
    public void undo()
    {
    	board.undo();
    	view.changed();
    }
    
    private class PitClicker extends MouseAdapter
    {
        private final Pit pit;

        public PitClicker(Pit pit) {
            this.pit = pit;
        }
        

        private void clickPit(MouseEvent e) {
            int pitIndex = pit.getIndex();
            board.updateBoard(pitIndex);
    		view.changed();
        }
        /*
    	private void clickPit(int pitIndex)
    	{
    		board.updateBoard(pitIndex);
    		view.changed();
    	}
        */
    }
}
