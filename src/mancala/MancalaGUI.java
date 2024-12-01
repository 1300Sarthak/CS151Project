package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MancalaGUI {
    private final MancalaBoard board;
    private final MancalaController controller;
    private MancalaView view;
    private JLabel turnLabel; 

    public MancalaGUI() {
        // ask for number of stones to start with (3 or 4)
        String stonesInput = JOptionPane.showInputDialog("Enter the number of stones per pit (3 or 4):");
        int stonesPerPit = Integer.parseInt(stonesInput);
        if (stonesPerPit != 3 && stonesPerPit != 4) {
            JOptionPane.showMessageDialog(null, "Invalid number of stones! Defaulting to 3.");
            stonesPerPit = 3; // Default to 3 if invalid input from user
        }

        this.board = new MancalaBoard(stonesPerPit); 
        this.view = new MancalaView(board, "Format1"); // Default to Format1
        this.controller = new MancalaController(board, view);
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Mancala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new FlowLayout());
        turnLabel = new JLabel("Current Turn: Player A"); //start with player A
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        turnPanel.add(turnLabel);

        frame.add(turnPanel, BorderLayout.NORTH); 
        frame.add(view.getPanel(), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Format buttons
        JButton format1Button = new JButton("Format 1");
        JButton format2Button = new JButton("Format 2");
        
        // Undo button
        JButton undoButton = new JButton("Undo");
        
        // End turn button
        JButton endTurnButton = new JButton("End Turn");

        // action listeners for buttons
        format1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.switchFormat("Format1");
                frame.revalidate();
                frame.repaint();
            }
        });

        format2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.switchFormat("Format2");
                frame.revalidate();
                frame.repaint();
            }
        });

        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.undo();
                view.getPanel().repaint();
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.switchPlayer();
                turnLabel.setText("Current Turn: " + board.getCurrentPlayer()); 
                view.getPanel().repaint();
            }
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
        new MancalaGUI(); 
    }
}
