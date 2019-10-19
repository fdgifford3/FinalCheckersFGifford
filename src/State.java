// Keeps track of Game State: Red's Turn, Black's Turn, Game Over
public interface State {
	public void turnFinished();
	public String getTurnText();
}
