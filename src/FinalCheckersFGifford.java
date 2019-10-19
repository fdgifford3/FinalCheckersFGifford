/*
 * Frederick Gifford
 * CPSC-60000 Week 8 Final Project: Checkers Game
 * Simple Checkers game utilizing four design patterns for final project
 * 10.19.2019
 * Prof Nowak
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class FinalCheckersFGifford extends JPanel {

	
    public static void main(String[] args) {
		JFrame window = new JFrame("Checkers - FGifford");
		FinalCheckersFGifford content = new FinalCheckersFGifford();
		window.setContentPane(content);
		window.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screensize.width - window.getWidth())/2,
                (screensize.height - window.getHeight())/2 );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setResizable(false);  
        window.setVisible(true);
	}

	public FinalCheckersFGifford() {
		setLayout(null);
        setPreferredSize( new Dimension(800,600) );

        setBackground(new Color(192,192,192));
        Board board = new Board();

        add(board);
        add(board.newGameButton);
        add(board.turnText);


        board.setBounds(20,20,492,492);
        board.newGameButton.setBounds(550, 120, 120, 50);
        board.turnText.setBounds(0, 520, 500, 30);
	}
	
}

