package edu.jsu.mcis;

public class TicTacToeView {

    private TicTacToeModel model;
    
    /* CONSTRUCTOR */
	
    public TicTacToeView(TicTacToeModel model) {
        
        this.model = model;
        
    }
	
    public void viewModel() {
        
        /* Print the board to the console (see examples) */
		
		
		System.out.print("\n\n");
		
		System.out.print("  "); 
        
		for(int j = 0; j<model.getWidth(); j++)     //Prints number along top of board
			System.out.print(j);
	
		System.out.print("\n\n");
		
		for(int i = 0; i<model.getWidth(); i++){
			System.out.print(i + " ");             //Prints numbers along side of board
			for(int j = 0; j<model.getWidth(); j++)	   //For loop to go through the whole grid
				System.out.print(model.getMark(i,j));
			System.out.print("\n");
				}
			
		
		System.out.print("\n\n");
		
	
	
	

    }

    public void showNextMovePrompt() {

        /* Display a prompt for the player's next move (see examples) */

        boolean xTurn;
		xTurn = model.isXTurn();
		if(xTurn == true){
			System.out.println("Player 1 (X) Move:");
			System.out.print("Enter the row and column numbers, separated by a space: ");}
		if(xTurn == false){
			System.out.println("Player 2 (O) Move:");
			System.out.print("Enter the row and column numbers, separated by a space: ");}

    }

    public void showInputError() {

        /* Display an error if input is invalid (see examples) */

        System.out.println("Invalid input");

    }

    public void showResult(String r) {

        /* Display final winner */

        System.out.println(r + "!");

    }
	
}