
public class Queen extends Piece {

	public Queen(String name, String colour, String imageName) {
		//Assigns a name, colour, and image location
		this.name = name;
		this.colour = colour;
		this.imageName = imageName;
		
	}
	
	public Queen () {
		
		
	}
	//Validates piece move
	public boolean validateMove(Square[][] SquareArray, int x, int y, int currentx, int currenty, boolean gameEnded) {
		//If game has ended, decline move
		if (gameEnded == true)
			return false;
		
		//If new position has a piece, then it must be a capture move
		if (SquareArray[x][y].SquarePiece != null)
			if (SquareArray[currentx][currenty].SquarePiece.colour == SquareArray[x][y].SquarePiece.colour)
				return false;
		
		//If it moves like the rook (in a cross)
		
		//If only x or y changes
		if ((x == currentx) || (y == currenty)) {
					
			if (x == currentx) {
						
				if (y > currenty) {
							
					//If Rook is moving Down
					for (int i = (y-1); i > currenty; i --) {
								
						//Checks if any pieces are blocking, then decline move
						if (SquareArray[x][i].SquarePiece != null) 
									
							return false;
								
					}
						
				} else if (y < currenty) {
							
					//If Rook is moving Up
					for (int i = (y+1); i < currenty; i ++) {
						
						//Checks if any pieces are blocking, then decline move
						if (SquareArray[x][i].SquarePiece != null)
				
							return false;
							
					}
				}
			} else if (y == currenty) {
						
				if (x > currentx) {
							
					//if Rook is moving Right
					for (int i = (x - 1); i > (currentx); i --) {
								
						//Checks if any pieces are blocking, then decline move
						if (SquareArray[i][y].SquarePiece != null)
									
							return false;	
					}
					
				} else if (x < currentx) {
							
					//If Rook is moving Left
					for (int i = (x+1); i < currentx; i ++) {
								
						//Checks if any pieces are blocking, then decline move
						if (SquareArray[i][y].SquarePiece != null)
							return false;
					}
				}
			}
		//If not declined, it is valid!
		return true;
		
		//If it moves like the bishop (diagonally)
		
		//Diagonal y = -x || Diagonal y = x
		} else if (((currentx - x) + (currenty - y) == 0) || (currentx - x) - (currenty - y) == 0) {
					
			if (x > currentx && y > currenty) {
						
				//Moving up-right
				for (int i = 1; i < (x-currentx) ; i ++) {
							
					//Checks if any pieces are blocking, then decline move
					if (SquareArray[x-i][y-i].SquarePiece != null) 
						return false;	
				}
						
			} else if (x < currentx && y < currenty) {
				//Moving down-left
						
				for (int i = 1; i < (currentx - x); i ++) {
							
					//Checks if any pieces are blocking, then decline move
					if (SquareArray[x+1][y+1].SquarePiece != null)
						return false;
				}
						
			} else if (x > currentx && y < currenty) {
				//Moving down-right
						
				for (int i = 1; i < (x - currentx); i ++) {
							
					//Checks if any pieces are blocking, then decline move
					if (SquareArray[x-1][y+1].SquarePiece != null)
						return false;
				}
			} else if (x < currentx && y > currenty) {
				//Moving up-left
						
				for (int i = 1; i < (currentx - x); i ++) {
							
					//Checks if any pieces are blocking, then decline move
					if (SquareArray[x+1][y-1].SquarePiece != null)
						return false;
							
				}
			}
			
			//If not declined, it is valid!
			return true;
		}
		//If it doesn't move in a cross or diagonally, decline!
		return false;
	}

}
