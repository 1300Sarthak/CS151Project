package mancala;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;

public class MancalaView extends JFrame implements MancalaListener {
    private final JPanel viewPanel;
    private final MancalaBoard board;
    private final ArrayList<Pit> pits;
    private JButton undoButton;
    private JButton nextTurnButton;
    private MancalaController controller;
    private FormatStrategy currentFormat;

    public MancalaView(MancalaBoard board, String format) {
        this.board = board;
        this.pits = new ArrayList<>(14);
        this.viewPanel = new JPanel(new BorderLayout());
        
        undoButton = new JButton("Undo");
        nextTurnButton = new JButton("Next Turn");
        
        switchFormat(format);
        board.attach(this);
    }
    
    public void setController(MancalaController controller) {
        this.controller = controller;
    }
    
    @Override
    public void changed() {
        for (int i = 0; i < pits.size(); i++) {
            Pit pit = pits.get(i);
            if (pit != null) {
                pit.setStoneCount(board.currentBoardState().get(i));
            }
        }
        repaint();
    }
    
    public JButton getUndoButton() {
        return undoButton;
    }
    
    public JButton getNextTurnButton() {
        return nextTurnButton;
    }
    
    public ArrayList<Pit> getPitList() {
        return pits;
    }
    
    public JPanel getPanel() {
        return viewPanel;
    }
    
    public void switchFormat(String format) {
        viewPanel.removeAll();
        FormatStrategy newFormat;
        
        if ("Format1".equalsIgnoreCase(format)) {
            newFormat = new Format1(board);
        } else if ("Format2".equalsIgnoreCase(format)) {
            newFormat = new Format2(board);
        } else {
            throw new IllegalArgumentException("Invalid format: " + format);
        }
        
        currentFormat = newFormat;
        viewPanel.add(newFormat.getPanel(), BorderLayout.CENTER);
        
        // Update pits list
        pits.clear();
        if (newFormat instanceof Format1) {
            pits.addAll(((Format1) newFormat).getPits());
        } else if (newFormat instanceof Format2) {
            pits.addAll(((Format2) newFormat).getPits());
        }
        
        if (controller != null) {
            controller.resetPitListeners();
        }
        
        viewPanel.revalidate();
        viewPanel.repaint();
    }
}