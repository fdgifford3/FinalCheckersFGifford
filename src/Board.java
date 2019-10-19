
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Board uses observer pattern to await actions from user

public class Board extends JPanel implements ActionListener, MouseListener {
	JButton newGameButton;
    JLabel turnText;
    
	CheckersData board;
	int fromRow = -1, fromColumn = -1;
	
	public Board() {
        setBackground(Color.BLACK);
        addMouseListener(this);
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        turnText = new JLabel("",JLabel.CENTER);
        turnText.setFont(new  Font("Arial", Font.BOLD, 18));
        turnText.setForeground(Color.green);
        board = CheckersData.getInstance();
        turnText.setText(board.turnText);
	}
	
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

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
        Object src = arg0.getSource();
        if (src == newGameButton) {
        	board.placePieces();
        	turnText.setText(board.turnText);
        	repaint();
        }
	}
	public void paintComponent(Graphics g) {
		
        g.setColor(Color.black);
        g.drawRect(0,0,getSize().width-1,getSize().height-1);
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
                    g.drawString("K", 21 + j*60, 48 + i*60);
            	} else if (board.getPiece(i,j) == CheckersData.BLACK_PROMOTED) {
                    g.setColor(Color.BLACK);
                    g.fillOval(12 + j*60, 12 + i*60, 45, 45);
                    g.setColor(Color.WHITE);
                    g.drawString("K", 21 + j*60, 48 + i*60);
            	}
            }
        }
    }
}
