package mancala;

import javax.swing.*;

public interface FormatStrategy {
    JPanel getPanel();
}
/*
public abstract class FormatStrategy {
    protected MancalaBoard board;
    
    public FormatStrategy(MancalaBoard board) {
        this.board = board;
    }

    public abstract JPanel getPanel();
}
*/
