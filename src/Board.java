
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Board uses observer pattern to await actions from user

public class Board extends JPanel implements ActionListener, MouseListener {
	JButton newGameButton;
    JLabel turnText;
    
    MoveLegality moveLegality;
    Move[] legalMoves;
	CheckersData board;

	int selectedRow = -1, selectedCol = -1;
	
	public Board() {
        board = CheckersData.getInstance();
		moveLegality = new MoveLegality();

        setBackground(Color.BLACK);
        addMouseListener(this);
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        turnText = new JLabel("",JLabel.CENTER);
        turnText.setFont(new  Font("Arial", Font.BOLD, 18));
        turnText.setForeground(Color.black);

        turnText.setText(board.turnText);
		legalMoves = moveLegality.getLegalMoves(board.getState());
	}
	
	//Did not use these
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// Get coordinates for where the user clicks and do the reverse of the math that
	// Was used to paint the squares on the board to decide which square was clicked
	@Override
	public void mousePressed(MouseEvent arg0) {
		int col = (arg0.getX() - 12) / 60;
        int row = (arg0.getY() - 12) / 60;
        if (col >= 0 && col < 8 && row >= 0 && row < 8)
            clickSquare(row,col);
	}

	//Did not use
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	//Just a new game button, repaint is causing a visual bug that I am having trouble figuring out
	@Override
	public void actionPerformed(ActionEvent arg0) {
        Object src = arg0.getSource();
        if (src == newGameButton) {
        	board.placePieces();
            legalMoves = moveLegality.getLegalMoves(board.getState());
        	turnText.setText(board.turnText);
        	repaint();
        }
	}
	
	public void clickSquare(int row, int col) {

		// Check to see if the square clicked is in the legal Moves array, if so, set the selected row and column
        for (int i = 0; i < legalMoves.length; i++) {
            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                selectedRow = row;
                selectedCol = col;
                repaint();
                return;
            }
        }

        //Now, check to see if the second square clicked is in the legal Moves array for the first selected square,
        //If so, make the move
        for (int i = 0; i < legalMoves.length; i++) {
            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
            && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                doMakeMove(legalMoves[i]);
                return;
            }
        }


    } 


    void doMakeMove(Move move) {

        board.makeMove(move);

        if (move.isJump()) {
            legalMoves = moveLegality.getLegalJumpsFrom(board.getState(),move.toRow,move.toCol);
            if (legalMoves != null) {

                turnText.setText("You must continue jumping.");
                selectedRow = move.toRow;  // Since only one piece can be moved, select it.
                selectedCol = move.toCol;
                repaint();
                return;
            }
        }


        if (board.getState().equals(board.getRedTurnState())) {
        	board.getState().turnFinished();
        	turnText.setText(board.getState().getTurnText());
            legalMoves = moveLegality.getLegalMoves(board.getState());
            if (legalMoves == null) {
            	board.gameOver = true;
            	board.getState().turnFinished();            	
            	turnText.setText("Black has no moves. Game Over  Red wins.");
            }
        } else if(board.getState().equals(board.getBlackTurnState())) {
        	board.getState().turnFinished();
        	turnText.setText(board.getState().getTurnText());
            legalMoves = moveLegality.getLegalMoves(board.getState());
            if (legalMoves == null) {
            	board.gameOver = true;
            	board.getState().turnFinished();
            	turnText.setText("Red has no moves. Game Over  Black wins.");
            }
        }

        selectedRow = -1;


        repaint();

    }  
	
	public void paintComponent(Graphics g) {
		
        g.setColor(Color.black);
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
        g.drawRect(1,1,getSize().width-2,getSize().height-2);

        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ( i % 2 != j % 2 ) {
                    g.setColor(Color.LIGHT_GRAY);
                }
                else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(6 + j*60, 6 + i*60, 60, 60);
                
                if (board.getPiece(i,j) == CheckersData.RED) {
                    g.setColor(Color.RED);
                    g.fillOval(12 + j*60, 12 + i*60, 45, 45);
                } else if (board.getPiece(i,j) == CheckersData.BLACK) {
                    g.setColor(Color.BLACK);
                    g.fillOval(12 + j*60, 12 + i*60, 45, 45);
            	} else if (board.getPiece(i,j) == CheckersData.RED_PROMOTED) {
                    g.setColor(Color.RED);
                    g.fillOval(12 + j*60, 12 + i*60, 45, 45);
                    g.setColor(Color.WHITE);
                    g.drawString("K", 30 + j*60, 40 + i*60);
            	} else if (board.getPiece(i,j) == CheckersData.BLACK_PROMOTED) {
                    g.setColor(Color.BLACK);
                    g.fillOval(12 + j*60, 12 + i*60, 45, 45);
                    g.setColor(Color.WHITE);
                    g.drawString("K", 30 + j*60, 40 + i*60);
            	}
            }
        }
        
        if (selectedRow >= 0) {
            g.setColor(Color.white);
            g.drawRect(2 + selectedCol*60, 2 + selectedRow*60, 59, 59);
            g.drawRect(3 + selectedCol*60, 3 + selectedRow*60, 57, 57);
            g.setColor(Color.blue);
            for (int i = 0; i < legalMoves.length; i++) {
                if (legalMoves[i].fromCol == selectedCol && legalMoves[i].fromRow == selectedRow) {
                    g.drawRect(2 + legalMoves[i].toCol*60, 2 + legalMoves[i].toRow*60, 59, 59);
                    g.drawRect(3 + legalMoves[i].toCol*60, 3 + legalMoves[i].toRow*60, 57, 57);
                }
            }
        }
    }
}
