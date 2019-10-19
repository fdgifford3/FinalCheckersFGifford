
public class BlackTurnState implements State {
	CheckersData checkersData;
	String blackTurnText = "Black's turn";

	
	public BlackTurnState (CheckersData checkersData) {
		this.checkersData = checkersData;
	}
	
	public void turnFinished() {
		if (checkersData.gameOver == true) {
			checkersData.setState(checkersData.getGameOverState());
		} else {
			checkersData.setState(checkersData.getRedTurnState());
		}

	}
	public String getTurnText() {
		return blackTurnText;
	}
	public int getPlayer() {
		return CheckersData.BLACK;
	}
	public int getPlayerKing() {
		return CheckersData.BLACK_PROMOTED;
	}
}
