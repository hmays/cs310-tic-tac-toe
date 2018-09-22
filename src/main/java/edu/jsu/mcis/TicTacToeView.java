package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeView extends JPanel implements ActionListener {
    
    TicTacToeModel model;

    private JButton[][] squares;
    private JPanel squaresPanel;
    private JLabel resultLabel;
	
    public TicTacToeView(TicTacToeModel model) {

        this.model = model;

        int width = model.getWidth();
		
		  
           
		   

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        squares = new JButton[width][width];
        squaresPanel = new JPanel(new GridLayout(width,width));
        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
		
        
        for (int row = 0; row < width; row++){
            
            for (int col = 0; col < width; col++){
              
                squares[row][col] = new JButton(); 
                squares[row][col].addActionListener(this);
                
                squares[row][col].setPreferredSize(new Dimension(64,64));
                squaresPanel.add(squares[row][col]);
				String rowS = String.valueOf(row);
				String colS = String.valueOf(col);
				squares[row][col].setName("Square" + rowS + colS);
                
            }
            
        }

        this.add(squaresPanel);
        this.add(resultLabel);
        
        resultLabel.setText("Welcome to Tic-Tac-Toe!");


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        /* Handle button clicks.  Extract the row and col values from the name
           of the button that was clicked, then make the mark in the grid using
           the Model's "makeMark()" method.  Finally, use the "updateSquares()"
           method to refresh the View.  If the game is over, show the result
           (from the Model's "getResult()" method) in the result label. */
        
        String name = ((JButton) event.getSource()).getName(); // Get button name
        String array [] = name.split("(?!^)");
        // INSERT YOUR CODE HERE
		
        //String nameRowCol = name.substring(7);
        int rowInt = Integer.parseInt(array[6]);
        int colInt = Integer.parseInt(array[7]);
		model.makeMark(rowInt, colInt);
		updateSquares();

    }
        
    public void updateSquares() {
		
		

        /* Loop through all View buttons and (re)set the text of each button
           to reflect the grid contents (use the Model's "getMark()" method). */
		      for(int x = 0; x < model.getWidth(); x++)
            {
                for (int y = 0; y < model.getWidth(); y++)
                {	 
					
					String text = model.getMark(x,y).toString();
					
			
                    squares[x][y].setText(text);
                }
            }

        if(model.isGameover())
        {
            showResult(model.getResult().toString().toUpperCase());
			
        }

    }
	
	public void viewModel(){
		
        squaresPanel.setVisible(true);
	}
        
    public void showResult(String message) {
		String upperCase = message.toUpperCase();
        resultLabel.setText(upperCase);
    }

}