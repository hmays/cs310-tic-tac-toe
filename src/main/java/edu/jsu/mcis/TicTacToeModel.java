package edu.jsu.mcis;

public class TicTacToeModel {
    
    private static final int DEFAULT_WIDTH = 3;
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a tie,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("Tie"), 
        NONE("none");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    private Mark[][] grid; /* Game grid */
    private boolean xTurn; /* True if X is current player */
    private int width;     /* Size of game grid */
    
    /* DEFAULT CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        /* No arguments (call main constructor; use default size) */
        
        this(DEFAULT_WIDTH);
		
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create grid (width x width) as a 2D Mark array */

        
		grid = new Mark[width][width];

        /* Initialize grid by filling every square with empty marks */

        for(int i=0; i<width; i++){
			for(int j=0; j<width; j++) {
				grid[i][j] = Mark.EMPTY;
			}
		}
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Place the current player's mark in the square at the specified
           location, but only if the location is valid and if the square is
           empty! */
			
		boolean valid = isValidSquare(row,col);
		boolean correctTurn = isXTurn();
		boolean markedSquare = isSquareMarked(row,col);
		
		boolean result = false;
		
		if (valid && !markedSquare) {
			
			if (xTurn)
				grid[row][col] = Mark.X;
			else
				grid[row][col] = Mark.O;
			
			xTurn = !xTurn;
			
			result = true;
			
		}
		
		return result;
		
		
		/*if((valid) && (correctTurn) && (!markedSquare)){
			grid[row][col] = Mark.X;
			xTurn = false;
			return true;
		}
		
		if((valid == true) && (correctTurn == false) && (markedSquare == false)){
			grid[row][col] = Mark.O;
			xTurn = true;
			return true;
		}*/
		
			
		//return false;
	

        
        
    }
	
    public boolean isValidSquare(int row, int col) {
        
        /* Return true if specified location is within grid bounds */
        
        if(((row < width) && (col < width)) && ((row>=0) && (col>=0)) ) {
			return true;
		}
		else
			return false;
        
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return true if square at specified location is marked */
		
		boolean valid = isValidSquare(row,col);
        
        //if(grid[row][col]==Mark.X || grid[row][col]==Mark.O && valid == true)
		if (valid && grid[row][col] == Mark.EMPTY)
			return false;
		else
			return true;
        
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return mark from the square at the specified location */
			boolean valid;
			valid = isSquareMarked(row,col);
		if(valid)
			return grid[row][col];
		else	
			return Mark.EMPTY;
		

        
            
    }
	
    public Result getResult() {
        
        /* Use isMarkWin() to see if X or O is the winner, if the game is a
           tie, or if the game is not over, and return the corresponding Result
           value */
        
		boolean tie;
		tie = isTie();
		boolean xWin;
		xWin = isMarkWin(Mark.X);
		boolean oWin;
		oWin = isMarkWin(Mark.O);
	
		if(oWin == true)
			return Result.O;
		if(xWin == true)
			return Result.X;
		if(tie == true)
			return Result.TIE;
		if(oWin != true && xWin != true && tie!= true)
			return Result.NONE;
		else
			return Result.NONE;
	 }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
		int count=0;
	
		/*check column*/
	 
		for(int j =0; j<width; j++){	
			for(int i = 0; i < width; i++){
				if(grid[j][i] == mark)
					count++;}
			if(count == width)
				return true;
			else
				count = 0;}
	

		/* check row*/
	
		for(int j =0; j<width; j++){	
			for(int i = 0; i < width; i++){
				if(grid[i][j] == mark)
					count++;}
			if(count == width)
				return true;
			else
				count = 0;}
	
		/*check diag*/
		for(int i =0; i<width; i++){
			if(grid[i][i] == mark)
				count++;}
		if(count == width)
			return true;
		else	
			count = 0;

		/*check backwards diag*/
		int column = 0;
		for(int i = width-1; i>=0; i--){
			
			if(grid[i][column] == mark)
				count++;
			column++;}
			if(count==width)
				return true;
			else
				return false;

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */

        int count = 0;
		for(int i =0; i<width; i++){
			for(int j = 0; j<width; j++)
				if(grid[i][j] == Mark.EMPTY)
					return false;}
		return true;
		
			

        
        
    }

    public boolean isGameover() {
        
        /* Return true if the game is over */
        
        return Result.NONE != getResult();
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
}