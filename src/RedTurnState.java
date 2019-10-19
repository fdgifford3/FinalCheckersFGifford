
public class RedTurnState implements State {
	CheckersData checkersData;
	String redTurnText = "Red's turn";

	
	public RedTurnState (CheckersData checkersData) {
		this.checkersData = checkersData;
		
	}
	
	public void turnFinished() {
		if (checkersData.gameOver == true) {
			checkersData.setState(checkersData.getGameOverState());
		} else {
			checkersData.setState(checkersData.getBlackTurnState());
		}

	}
	
	public String getTurnText() {
		return redTurnText;
	}
	public int getPlayer() {
		return CheckersData.RED;
	}
	public int getPlayerKing() {
		return CheckersData.RED_PROMOTED;
	}
}
