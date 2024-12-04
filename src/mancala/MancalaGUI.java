package mancala;

import java.awt.*;
import javax.swing.*;

public class MancalaGUI {
    private final MancalaBoard board;
    private MancalaView view;
    //private JLabel turnLabel;
    private JLabel undoCountLabel;

    public MancalaGUI() {
        String stonesInput = JOptionPane.showInputDialog("Enter the number of stones per pit (3 or 4):");
        int stonesPerPit = Integer.parseInt(stonesInput);
        if (stonesPerPit != 3 && stonesPerPit != 4) {
            JOptionPane.showMessageDialog(null, "Invalid number of stones! Defaulting to 3");
            stonesPerPit = 3;
        }

        this.board = new MancalaBoard(stonesPerPit);
        this.view = new MancalaView(board, "Format1");
        new MancalaController(board, view); 
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Mancala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Status Panel (Top)x
        JPanel statusPanel = new JPanel(new FlowLayout());
        //turnLabel = new JLabel("Current Turn: Player A");
        undoCountLabel = new JLabel("Undos remaining: " + (3 - board.getSelectedUndos()));
        //turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        undoCountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        //statusPanel.add(turnLabel);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(undoCountLabel);
        frame.add(statusPanel, BorderLayout.NORTH);

        // Game Board (Center)
        frame.add(view.getPanel(), BorderLayout.CENTER);

        // Control Panel (Bottom)
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton format1Button = new JButton("Format 1");
        JButton format2Button = new JButton("Format 2");
        JButton undoButton = new JButton("Undo");
        JButton endTurnButton = new JButton("End Turn");

        // Action Listeners
        format1Button.addActionListener(e -> {
            view.switchFormat("Format1");
            frame.revalidate();
            frame.repaint();
        });

        format2Button.addActionListener(e -> {
            view.switchFormat("Format2");
            frame.revalidate();
            frame.repaint();
        });

        undoButton.addActionListener(e -> {
        	if (board.checkStackEmpty())
        	{
        		JOptionPane.showMessageDialog(frame, "Please make a move first.");
        		return;
        	}
            if (board.undo()) {
                undoCountLabel.setText("Undos remaining: " + (3 - board.getSelectedUndos()));
                view.changed();
            } else {
                JOptionPane.showMessageDialog(frame, "No more undos available for this turn!");
                return;
            }
        });

        endTurnButton.addActionListener(e -> {
            board.progressTurn();
            //turnLabel.setText("Current Turn: " + board.getCurrentPlayer());
            undoCountLabel.setText("Undos remaining: 3");
            view.changed();
        });

        buttonPanel.add(format1Button);
        buttonPanel.add(format2Button);
        buttonPanel.add(undoButton);
        buttonPanel.add(endTurnButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MancalaGUI::new);
    }
}