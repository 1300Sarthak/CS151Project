package mancala;


public class MancalaGUI {
    public static void main(String[] args) {
        int initialStones = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of stones per pit (this must be either 3 or 4):"));
        MancalaBoard board = new MancalaBoard(initialStones);
        MancalaView view = new MancalaView(board);
        MancalaController controller = new MancalaController(board, view);

        JFrame frame = new JFrame("Mancala Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
