package mancala;

public class MancalaView extends JPanel {
    private MancalaBoard board;

    public MancalaView(MancalaBoard board) {
        this.board = board;
        //setup the GUI stuff here such as the pits and the Mancalas
    }

    public void refresh() {
        //this will update the view based on what is currently going on the board
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //I will draw the board, pits, and stones here
    }
