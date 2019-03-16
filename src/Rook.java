

public class Rook extends Piece {

	public Rook(String name, String colour, String imageName) {
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
		
		//If only x or y changes (Rook's movement)
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
			//If move is still not declined, then it is valid!
			return true;
		}
		//If it does move like a rook, decline move (from if statement above)
		return false;
	}
}
	//For self references: currentx = position right now, x = new selected position
