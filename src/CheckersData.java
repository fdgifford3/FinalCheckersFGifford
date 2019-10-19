//Uses State pattern to keep track of which turn it is (or if the game is over, though that is mostly so that there is
//A non Red/Black state to avoid running into issues

//Also going to use Singleton on the CheckersData as this object will be used in multiple places but it has to be the only existing object
import java.util.ArrayList;

public class CheckersData {
	private static CheckersData uniqueInstance = new CheckersData();
	
	Boolean gameOver;
	int[][] checkersBoard;
	static final int RED = 1, BLACK = 2, RED_PROMOTED = 3, BLACK_PROMOTED = 4, EMPTY = 5;
	String turnText;
	
	State redTurnState;
	State blackTurnState;
	State gameOverState;
	
	State state;
	
	private CheckersData() {
		
		redTurnState = new RedTurnState(this);
		blackTurnState = new BlackTurnState(this);
		gameOverState = new GameOverState(this);
		
	
		
		checkersBoard = new int[8][8];
		placePieces();
	}
	
	public static CheckersData getInstance(){
		return uniqueInstance;
	}
	
	public void placePieces() {
		state = blackTurnState; //Black goes first
		gameOver = false;
		turnText = state.getTurnText();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				//On the checkersboard, the black squares are where pieces are placed and
				//These hold to the truth that if the row is even, the column will be odd and
				//If the row is odd then the column is even (0,7) (1,6) (7,0), etc.
				//So every square where row % 2 does not equal column % 2 is a legal square on our board
				
				//Rows 0,1,2 get Red and rows 5,6,7 get black. All other spaces start empty
				if((i % 2 != j % 2) && (i < 3)) {
					checkersBoard[i][j] = CheckersData.RED;
				} else if ((i % 2 != j % 2) && (i > 4)) {
					checkersBoard[i][j] = CheckersData.BLACK;
				} else {
					checkersBoard[i][j] = CheckersData.EMPTY;
				}
			}
		}
	}
	
	public int getPiece(int i, int j) {
		return checkersBoard[i][j];
	}
	
	public void makeMove(Move move) {
		makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
	}
	
	public void makeMove(int fromRow, int fromCol, int toRow, int toCol){
		checkersBoard[toRow][toCol] = checkersBoard[fromRow][fromCol]; //move piece to new location
		checkersBoard[fromRow][fromCol] = CheckersData.EMPTY;  //remove piece from where it was
		
		//if it was a jump, also remove piece that was jumped
		if((fromRow - toRow > 1) || (fromRow - toRow < -1)) {
			checkersBoard[(fromRow - toRow) / 2][(fromCol - toCol) / 2] = CheckersData.EMPTY;
		}
		
		//if red reached row 7 or black reached row 0, promote
        if (toRow == 0 && checkersBoard[toRow][toCol] == CheckersData.RED)
        	checkersBoard[toRow][toCol] = CheckersData.RED_PROMOTED;
        if (toRow == 7 && checkersBoard[toRow][toCol] == CheckersData.BLACK)
        	checkersBoard[toRow][toCol] = CheckersData.BLACK_PROMOTED;
	}
	
	
	
	public State getState() {
		return state;
	}
    
    public State getRedTurnState() {
        return redTurnState;
    }
    
    public State getBlackTurnState() {
        return blackTurnState;
    }
    
    public State getGameOverState() {
        return gameOverState;
    }
    
	public void setState(State state) {
		this.state = state;
		this.turnText = state.getTurnText();
	}
	
	
	
	
}
