
public class GameOverState implements State {
	CheckersData checkersData;
	String gameOverTurnText = "Game is over!";
	
	public GameOverState (CheckersData checkersData) {
		this.checkersData = checkersData;
	}
	
	public void turnFinished() {}
	public String getTurnText() {
		return gameOverTurnText;
	}

}
