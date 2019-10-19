import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StateChangeTest {

	@Test
	void test() {
		CheckersData board = CheckersData.getInstance();
		//Check that initial state has set pieces to black and black promoted
		assertEquals(CheckersData.BLACK,board.getState().getPlayer());
		assertEquals(CheckersData.BLACK_PROMOTED,board.getState().getPlayerKing());
		//Try changing state to red and see if players change correctly to red and red promoted
		board.setState(board.getRedTurnState());
		assertEquals(CheckersData.RED,board.getState().getPlayer());
		assertEquals(CheckersData.RED_PROMOTED,board.getState().getPlayerKing());
		//Finally, change it back and make sure they went back to black and black promoted
		board.setState(board.getBlackTurnState());
		assertEquals(CheckersData.BLACK,board.getState().getPlayer());
		assertEquals(CheckersData.BLACK_PROMOTED,board.getState().getPlayerKing());

	}

}
