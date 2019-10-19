
public class Move {
	int fromRow, fromCol, toRow, toCol;
	
	public Move(int fromRow, int fromCol, int toRow, int toCol){
		this.fromRow = fromRow;
		this.fromCol = fromRow;
		this.toRow = fromRow;
		this.toCol = fromRow;
	}
	
	public boolean isJump() {
		return ((fromRow - toRow > 1) || (fromRow - toRow < -1));
	}
}
