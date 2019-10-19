import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MoveLegalityTest {

	@Test
	void test() {
		// Two for the price of one test, this also tests that the Singleton for CheckersData is creating a board
		// Could have made a separate test for this, but I knew it was working and wanted to focus on what I thought
		// Would be more valuable tests
		CheckersData board = CheckersData.getInstance();
		State args = new BlackTurnState(board);
		MoveLegality moveLegality = new MoveLegality();
		Move[] moves = moveLegality.getLegalMoves(args);
		//important test, when game starts up, the moves list must not be null
		assertNotNull(moves);
	}

}
