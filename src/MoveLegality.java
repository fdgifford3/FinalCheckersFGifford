import java.util.ArrayList;

public class MoveLegality {

	CheckersData board = CheckersData.getInstance();
	
	public Move[] getLegalMoves(State state) {

        if (state.equals(board.gameOverState))
            return null;
        
        //make the algorithm color neutral
        int player = state.getPlayer();
        int playerKing= state.getPlayerKing();
        

        ArrayList<Move> moves = new ArrayList<Move>(); 

        // Jumps are mandatory according to my brief Google research of Checkers rules, so search for jumps first
        // All moves checking goes in all four directions, upper left, upper right, lower left, and lower right
        // Logic for whether or not the jump/move is allowed is delegated to methods below
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.checkersBoard[row][col] == player || board.checkersBoard[row][col] == playerKing) {
                    if (canJump(row, col, row+1, col+1, row+2, col+2))
                        moves.add(new Move(row, col, row+2, col+2));
                    if (canJump(row, col, row-1, col+1, row-2, col+2))
                        moves.add(new Move(row, col, row-2, col+2));
                    if (canJump(row, col, row+1, col-1, row+2, col-2))
                        moves.add(new Move(row, col, row+2, col-2));
                    if (canJump(row, col, row-1, col-1, row-2, col-2))
                        moves.add(new Move(row, col, row-2, col-2));
                }
            }
        }

        // This method could be adapted for rule changes to allow for jumps to be non-mandatory. Simple
        // Check to see if moves list is empty is creating the mandatory jump rule here
        if (moves.size() == 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board.checkersBoard[row][col] == player || board.checkersBoard[row][col] == playerKing) {
                        if (canMove(row,col,row+1,col+1)) {
                            moves.add(new Move(row,col,row+1,col+1));
                        }
                        if (canMove(row,col,row-1,col+1)) {
                            moves.add(new Move(row,col,row-1,col+1));
                        }
                        if (canMove(row,col,row+1,col-1)) {
                            moves.add(new Move(row,col,row+1,col-1));
                        }
                        if (canMove(row,col,row-1,col-1)) {
                            moves.add(new Move(row,col,row-1,col-1));
                        }
                    }
                }
            }
        }

        //No legal moves means we return a null array and the game is over

        if (moves.size() == 0) {

            return null;
        }
        
        //If the moves list is not empty then we can add the arraylist of moves to a Move[] and send it back to the board
        Move[] moveArray = new Move[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            moveArray[i] = moves.get(i);
            
        }
        return moveArray;

    }
	
	
	Move[] getLegalJumpsFrom(State state, int fromRow, int fromCol) {
		if (state.equals(board.gameOverState)) {
			return null;
		}
		int player = state.getPlayer();
        int playerKing= state.getPlayerKing();
	        
        ArrayList<Move> moves = new ArrayList<Move>();
        if (board.checkersBoard[fromRow][fromCol] == player || board.checkersBoard[fromRow][fromCol] == playerKing) {
        	 if (canJump(fromRow, fromCol, fromRow+1, fromCol+1, fromRow+2, fromCol+2))
                 moves.add(new Move(fromRow, fromCol, fromRow+2, fromCol+2));
             if (canJump(fromRow, fromCol, fromRow-1, fromCol+1, fromRow-2, fromCol+2))
                 moves.add(new Move(fromRow, fromCol, fromRow-2, fromCol+2));
             if (canJump(fromRow, fromCol, fromRow+1, fromCol-1, fromRow+2, fromCol-2))
                 moves.add(new Move(fromRow, fromCol, fromRow+2, fromCol-2));
             if (canJump(fromRow, fromCol, fromRow-1, fromCol-1, fromRow-2, fromCol-2))
                 moves.add(new Move(fromRow, fromCol, fromRow-2, fromCol-2));
        }
        if (moves.size() == 0)
            return null;
        else {
            Move[] moveArray = new Move[moves.size()];
            for (int i = 0; i < moves.size(); i++) {
                moveArray[i] = moves.get(i);

            }
            return moveArray;
        }
    }
	
	//Need to keep track of the from row, to row, and the row being jumped in the case of a jump, can't jump same color piece
	//Only kings can move bi-directionally, can only move into empty spaces
	 private boolean canJump(int r1, int c1, int r2, int c2, int r3, int c3) {

         if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
             return false;

         if (board.checkersBoard[r3][c3] != CheckersData.EMPTY)
             return false;

         if (board.state.equals(board.redTurnState)) {
             if (board.checkersBoard[r1][c1] == CheckersData.RED && r3 < r1)
                 return false; 
             if (board.checkersBoard[r2][c2] != CheckersData.BLACK && board.checkersBoard[r2][c2] != CheckersData.BLACK_PROMOTED)
                 return false;
             return true; 
         }
         else {
             if (board.checkersBoard[r1][c1] == CheckersData.BLACK && r3 > r1)
                 return false; 
             if (board.checkersBoard[r2][c2] != CheckersData.RED && board.checkersBoard[r2][c2] != CheckersData.RED_PROMOTED)
                 return false;
             return true;
         }

     }

	//Only kings can move bi-directionally, can only move into empty spaces
     private boolean canMove(int r1, int c1, int r2, int c2) {

         if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
             return false;

         if (board.checkersBoard[r2][c2] != CheckersData.EMPTY)
             return false; 

         if (board.state.equals(board.redTurnState)) {
             if (board.checkersBoard[r1][c1] == CheckersData.RED && r2 < r1)
                 return false; 
             return true; 
             }
         else {
             if (board.checkersBoard[r1][c1] == CheckersData.BLACK && r2 > r1)
                 return false;
             return true;
         }

     }
	
}

