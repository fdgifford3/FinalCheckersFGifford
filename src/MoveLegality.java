import java.util.ArrayList;

public class MoveLegality {

	CheckersData board = CheckersData.getInstance();
	
	public Move[] getLegalMoves(State state) {

        if (board.state.equals(board.gameOverState))
            return null;
        
        //make the algorithm color neutral
        int player;
        int playerKing;
        if (board.state.equals(board.redTurnState)) {
        	player = CheckersData.RED;
        	playerKing = CheckersData.RED_PROMOTED;
        }
        else {
        	player = CheckersData.BLACK;
            playerKing = CheckersData.BLACK_PROMOTED;
        }

        ArrayList<Move> moves = new ArrayList<Move>(); 

        // Jumps are mandatory, so search for jumps first
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


        if (moves.size() == 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board.checkersBoard[row][col] == player || board.checkersBoard[row][col] == playerKing) {
                        if (canMove(row,col,row+1,col+1))
                            moves.add(new Move(row,col,row+1,col+1));
                        if (canMove(row,col,row-1,col+1))
                            moves.add(new Move(row,col,row-1,col+1));
                        if (canMove(row,col,row+1,col-1))
                            moves.add(new Move(row,col,row+1,col-1));
                        if (canMove(row,col,row-1,col-1))
                            moves.add(new Move(row,col,row-1,col-1));
                    }
                }
            }
        }


        if (moves.size() == 0)
            return null;
        else {
            Move[] moveArray = new Move[moves.size()];
            for (int i = 0; i < moves.size(); i++)
                moveArray[i] = moves.get(i);
            return moveArray;
        }

    }
	 private boolean canJump(int r1, int c1, int r2, int c2, int r3, int c3) {

         if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
             return false;

         if (board.checkersBoard[r3][c3] != CheckersData.EMPTY)
             return false;

         if (board.state.equals(board.redTurnState)) {
             if (board.checkersBoard[r1][c1] == CheckersData.RED && r3 > r1)
                 return false; 
             if (board.checkersBoard[r2][c2] != CheckersData.BLACK && board.checkersBoard[r2][c2] != CheckersData.BLACK_PROMOTED)
                 return false;
             return true; 
         }
         else {
             if (board.checkersBoard[r1][c1] == CheckersData.BLACK && r3 < r1)
                 return false; 
             if (board.checkersBoard[r2][c2] != CheckersData.RED && board.checkersBoard[r2][c2] != CheckersData.RED_PROMOTED)
                 return false;
             return true;
         }

     }


     private boolean canMove(int r1, int c1, int r2, int c2) {

         if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
             return false;

         if (board.checkersBoard[r2][c2] != CheckersData.EMPTY)
             return false; 

         if (board.state.equals(board.redTurnState)) {
             if (board.checkersBoard[r1][c1] == CheckersData.RED && r2 > r1)
                 return false; 
             return true; 
             }
         else {
             if (board.checkersBoard[r1][c1] == CheckersData.BLACK && r2 < r1)
                 return false;
             return true;
         }

     }
	
}
