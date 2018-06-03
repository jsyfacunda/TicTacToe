import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;


public class TicTacToeGUI extends JFrame{
    //variables
    private TicTacToeLogic board = new TicTacToeLogic("", "");
    private static JButton buttons[][] = new JButton[3][3];
    JFrame frame;
    JPanel panel;

    //Player1 variables
    JLabel p1;
    JLabel p1Name;
    JLabel p1WinsLabel;
    JLabel p1LossesLabel;
    JTextField p1NameEntry;
    Integer p1Wins = 0;
    Integer p1Lossess = 0;

    //Player2 variables
    JLabel p2;
    JLabel p2Name;
    JLabel p2WinsLabel;
    JLabel p2LossesLabel;
    JTextField p2NameEntry;
    Integer p2Wins = 0;
    Integer p2Lossess = 0;

/*
     why is this not green
*/

    //AI Player
    JLabel p3;


    //Button Menu variables
    JButton newGameButton;
    JButton resetButton;
    JButton exitButton;

    JLabel message = new JLabel("Press 'New Game' to start playing!");


    public TicTacToeGUI() {
        frame = new JFrame ("Tic Tac Toe");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        //Makes a panel for the score board
        JPanel scoreBoardContainer = new JPanel();
        scoreBoardContainer.setLayout(new GridLayout(1,2));

        //Make a panel for p1 and p2 in the scoreboard container
        JPanel p1Pane1 = new JPanel();
        p1Pane1.setLayout(new BoxLayout(p1Pane1, BoxLayout.Y_AXIS));
        JPanel p2Pane1 = new JPanel();
        p2Pane1.setLayout(new BoxLayout(p2Pane1, BoxLayout.Y_AXIS));
        p1 = new JLabel("P1 (x): ");
        p1Name = new JLabel("Name: ");
        p1NameEntry = new JTextField("P1");
        p1WinsLabel = new JLabel("Wins: " + board.players[0].getWins());
        p1LossesLabel = new JLabel("Losses: " + board.players[0].getLosses());
        //Add objects p1Pane1
        p1Pane1.add(p1);
        p1Pane1.add(p1Name);
        p1Pane1.add(p1NameEntry);
        p1Pane1.add(p1WinsLabel);
        p1Pane1.add(p1LossesLabel);
        p1Pane1.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        p1Pane1.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 30, 10, 10), new EtchedBorder()));

        p2 = new JLabel("Player 2 (o): ");
        p2Name = new JLabel("Name: ");
        p2NameEntry = new JTextField("Player 2");
        p2WinsLabel = new JLabel("Wins: " + board.players[1].getWins());
        p2LossesLabel = new JLabel("Losses: " + board.players[1].getLosses());
        //Add objects to the p2Panel
        p2Pane1.add(p2);
        p2Pane1.add(p2Name);
        p2Pane1.add(p2NameEntry);
        p2Pane1.add(p2WinsLabel);
        p2Pane1.add(p2LossesLabel);
        p2Pane1.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        p2Pane1.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 30), new EtchedBorder()));
        scoreBoardContainer.add(p1Pane1);
        scoreBoardContainer.add(p2Pane1);
        frame.add(scoreBoardContainer, BorderLayout.NORTH);
        frame.pack();

        //Make a panel for the TTT board
        panel = new JPanel();
        panel.setLayout(new GridLayout (3, 3));
        panel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
        panel.setBackground(Color.gray);

        //TTT Buttons
        int[] puns = {0,1,2};
        for(int x : puns){
            for(int y : puns){
                buttons[x][y] = new JButton();
                panel.add(buttons[x][y]);
                buttons[x][y].addActionListener(e -> {
                    String p1Namo = p1NameEntry.getText().replace("Name: ", "");
                    String p2Namo = p2NameEntry.getText().replace("Name: ", "");
                    //set player names
                    board.getPlayers()[0].setName(p1Namo);
                    board.getPlayers()[1].setName(p2Namo);

                    if(p1Namo.equals("") || p2Namo.equals("") || p1Namo.equals(p2Namo)){
                        JOptionPane.showMessageDialog(null, "Wrong player names.");
                    } else if(!board.isFinished()) {
                        char move = board.setChoice(x, y);
                        if (move == 'X') {
                            message.setText("It's " + board.getPlayers()[1].getName() + "'s turn!");
                            //place X for player ones turn
                            try {
                                Image img = ImageIO.read(new File("./x.png"));
                                buttons[x][y].setIcon(new ImageIcon(img));
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        } else if (move == 'O') {
                            message.setText("It's " + board.getPlayers()[0].getName() + "'s turn!");
                            try {
                                Image img = ImageIO.read(new File("./o.png"));
                                buttons[x][y].setIcon(new ImageIcon(img));
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        }
                        //checks win and updates it
                        if (board.checkForWin()) {
                            if(board.getCurrPlayer().equals(board.getPlayers()[0])){
                                message.setText(board.getPlayers()[1].getName() + " wins!");
                            } else {
                                message.setText(board.getPlayers()[0].getName() + " wins!");
                            }
                            p1WinsLabel.setText("wins: " + board.players[0].getWins());
                            p1LossesLabel.setText("losses: " + board.players[0].getLosses());
                            p2WinsLabel.setText("wins: " + board.players[1].getWins());
                            p2LossesLabel.setText("losses: " + board.players[1].getLosses());
                            disableButtons();
                        }
                    }
                });
            }
        }
        disableButtons();
        frame.getContentPane().add (panel);
        frame.pack();
        frame.setSize(500, 500);

        //bottom of GUI buttons
        panel = new JPanel();
        panel.setLayout(new GridLayout (2, 1));
        JPanel buttonMenu = new JPanel();
        buttonMenu.setLayout(new FlowLayout(FlowLayout.CENTER));

        //new game button
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            String p1Nama = p1NameEntry.getText().replace("Name: ", "");
            String p2Nama = p2NameEntry.getText().replace("Name: ", "");
            if(p1Nama.equals("") || p2Nama.equals("") || p1Nama.equals(p2Nama)){
                JOptionPane.showMessageDialog(null, "Illegal player name(s).");
            } else {
                board.getPlayers()[0].setName(p1Nama);
                board.getPlayers()[1].setName(p2Nama);

                //update
                message.setText("It's " + board.getPlayers()[0].getName() + "'s turn!");
                //clear
                wipeBoard();
                board.newGame();
                board.setFinished(false);
            }
        });

        //reset game button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            String warning = "Are you sure you want to reset the game?";
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, warning, "Confirm Reset", dialogButton);
            if(dialogResult == 0) {
                message.setText("click 'New Game' to start playing");
                wipeBoard();
                board.reset();
                board.setFinished(true);
                disableButtons();
                p1NameEntry.setText("Player 1");
                p2NameEntry.setText("Player 2");
                p1WinsLabel.setText("Wins: " + board.players[0].getWins());
                p1LossesLabel.setText("Losses: " + board.players[0].getLosses());
                p2WinsLabel.setText("Wins: " + board.players[1].getWins());
                p2LossesLabel.setText("Losses: " + board.players[1].getLosses());
            }
        });

        //make an exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {System.exit(0);});
        buttonMenu.add(newGameButton);
        buttonMenu.add(resetButton);
        buttonMenu.add(exitButton);
        buttonMenu.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        buttonMenu.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder()));
        panel.add(buttonMenu);

        JPanel messageBox = new JPanel();
        messageBox.setLayout(new FlowLayout(FlowLayout.CENTER));
        messageBox.add(message);
        messageBox.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        messageBox.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20, 20, 20, 20), new EtchedBorder()));
        panel.add(messageBox);

        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    //resets all icons on board
    public void wipeBoard(){
        for(JButton[] row : buttons){
            for(JButton each : row){
                each.setBackground(Color.BLUE);
                each.setOpaque(true);
                each.setIcon(null);
            }
        }
    }

    //set background to yellow for non active
    public void disableButtons(){
        for(JButton[] row : buttons){
            for(JButton each : row){
                each.setBackground(Color.YELLOW);
                each.setOpaque(true);
            }
        }
    }


}