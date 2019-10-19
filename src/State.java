// Keeps track of Game State: Red's Turn, Black's Turn, Game Over
public interface State {
	public void turnFinished();
	public int getPlayer();
	public int getPlayerKing();
	public String getTurnText();
}
