package mancala;

/**
 * MancalaGUI.java : Fall 2024 Dr. Kim's CS151 Team Project Solution.
 * @author Sarthak Sethi, Vincent Pangilinan, Nikki Huynh.
 * @version 1.0 12/4/2024
 */

import java.awt.*;
import javax.swing.*;

public class MancalaGUI {
    private final MancalaBoard board;
    private MancalaView view;
    private JLabel undoCountLabel;

    public MancalaGUI() {
        String stonesInput = JOptionPane.showInputDialog("Enter the number of stones per pit (3 or 4):");
        int stonesPerPit;
        try {
            stonesPerPit = Integer.parseInt(stonesInput);
            if (stonesPerPit != 3 && stonesPerPit != 4) {
                JOptionPane.showMessageDialog(null, "Invalid number of stones! Defaulting to 3");
                stonesPerPit = 3;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Defaulting to 3 stones.");
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
        undoCountLabel = new JLabel("Undos remaining: " + (3 - board.getSelectedUndos()));;
        undoCountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
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
            if (board.undo()) {
                undoCountLabel.setText("Undos remaining: " + (3 - board.getSelectedUndos()));
                view.changed();
            } else {
                if (board.getSelectedUndos() >= 3) {
                    JOptionPane.showMessageDialog(frame, "No more undos available for this turn!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Cannot undo at this time.");
                }
            }
        });

        endTurnButton.addActionListener(e -> {
            board.progressTurn();
            undoCountLabel.setText("Undos remaining: 3");
            view.changed();
        });

        buttonPanel.add(format1Button);
        buttonPanel.add(format2Button);
        buttonPanel.add(undoButton);
        buttonPanel.add(endTurnButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame properties
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MancalaGUI::new);
    }
}