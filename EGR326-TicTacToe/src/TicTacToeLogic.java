public class TicTacToeLogic {
    //variables
    private Player player;
    private boolean isFinished = true;
    private char[][] gameBoard = {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
    Player[] players = new Player[2];

    //constructor
    public TicTacToeLogic(String p1, String p2) {
        players[0] = new Player(p1);
        players[1] = new Player(p2);
        player = players[0];
    }

    //checks to see if a space is occupied or not, returns boolean
    public boolean isOccupied(int row, int col){
        return gameBoard[row][col] != ' ';
    }

    //checks to see if game is finished
    public boolean isFinished() {return isFinished;}

    //setter
    public void setFinished(boolean f) {isFinished = f;}

    //sets the choice of player, checks if occupied already first
    public char setChoice(int row, int col) {
        if(!isOccupied(row, col)){
            if(player.equals(players[0])){
                gameBoard[row][col] = 'X';
                player = players[1];
                return 'X';
            } else{
                gameBoard[row][col] = 'O';
                player = players[0];
                return 'O';
            }
        }
        return ' ';
    }

    //getters
    public Player[] getPlayers() {return players;}
    public Player getCurrPlayer() {return player;}

    //creates a new game
    public void newGame() {
        player = players[0];
        //clear the playing board from O's and X's
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gameBoard[i][j] = ' ';
            }
        }
    }

    //creates new game and resets scores
    public void reset() {
        newGame();
        players[0].reset();
        players[1].reset();
    }

    //checks to see if a player has won the game
    public boolean checkForWin() {
        StringBuilder straight = new StringBuilder();
        StringBuilder across = new StringBuilder();

        for(int i = 0; i < 3; i++){
            straight.append("|");
            across.append("|");
            for(int j = 0; j < 3; j++){
                straight.append(gameBoard[i][j]);
                across.append(gameBoard[j][i]);
            }
        }
        Player win = null;

        if(straight.toString().contains("XXX")){
            win = players[0];
        } else if(straight.toString().contains("OOO")){
            win = players[1];
        } else if(across.toString().contains("XXX")){
            win = players[0];
        } else if(across.toString().contains("OOO")){
            win = players[1];
        } else if(straight.charAt(1) == 'X' && straight.charAt(6) == 'X' && straight.charAt(11) == 'X'){
            win = players[0];
        } else if(straight.charAt(3) == 'X' && straight.charAt(6) == 'X' && straight.charAt(9) == 'X'){
            win = players[0];
        } else if(straight.charAt(1) == 'O' && straight.charAt(6) == 'O' && straight.charAt(11) == 'O'){
            win = players[1];
        } else if(straight.charAt(3) == 'O' && straight.charAt(6) == 'O' && straight.charAt(9) == 'O'){
            win = players[1];
        }

        if(win != null && win.equals(players[0])){
            players[0].addWin();
            players[1].addLoss();
            isFinished = true;
            return true;
        } else if(win != null && win.equals(players[1])){
            players[1].addWin();
            players[0].addLoss();
            isFinished = true;
            return true;
        }
        return false;
    }
}