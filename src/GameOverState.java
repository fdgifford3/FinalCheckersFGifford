
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
	public int getPlayer() {
		return 0;
	}
	public int getPlayerKing() {
		return 0;
	}
}
