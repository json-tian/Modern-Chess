
public class Bishop extends Piece {

	public Bishop(String name, String colour, String imageName) {
		//Assigns a name, colour, and image location
		this.name = name;
		this.colour = colour;
		this.imageName = imageName;
		
	}
	//Validates piece move
	public boolean validateMove(Square[][] SquareArray,int x, int y, int currentx, int currenty, boolean gameEnded){
		
		//If game has ended, decline move
		if (gameEnded == true)
			return false;
		
		//If destination of piece colour is same as selected piece, decline move
		if (SquareArray[x][y].SquarePiece != null)
			if (SquareArray[currentx][currenty].SquarePiece.colour == SquareArray[x][y].SquarePiece.colour)
				return false;
		
		//If it is on a diagonal
		
		//Diagonal y = -x || Diagonal y = x
		if (((currentx - x) + (currenty - y) == 0) || (currentx - x) - (currenty - y) == 0) {
			
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
			//If move is not declined, it is valid!
			return true;
		}
		//If it is not on a diagonal, decline move (from if statement above)
		return false;
			
	}
}	//currentx = position right now, x = new selected position