
public class Pawn extends Piece {

	public Pawn(String name, String colour, String imageName) {
		//Assigns a name, colour, and image location
		this.name = name;
		this.colour = colour;
		this.imageName = imageName;
		
	}
	//Validates piece move
	public boolean validateMove(Square[][] SquareArray, int x, int y, int currentx, int currenty, boolean gameEnded) {
		
		//If game has ended, decline move
		if (gameEnded == true)
			return false;
		
		//If new position has a piece, then it must be a capture move
		if (SquareArray[x][y].SquarePiece != null)
			//If piece in new position is its own colour, decline move
			if (SquareArray[currentx][currenty].SquarePiece.colour == SquareArray[x][y].SquarePiece.colour)
				return false;
		//If pawn is white
		if (SquareArray[currentx][currenty].SquarePiece.colour == "W") {
			
			//If target square has a piece
			if (SquareArray[x][y].SquarePiece != null) {
				
				//If target square is one diagonal square away, accept move
				if (x == (currentx + 1) && y == (currenty - 1)) {
					
					return true;
				}
				if (x == (currentx - 1) && y  == (currenty - 1)) {
					
					return true;
				}
			
			//If target square does not have a piece
			} else if (SquareArray[x][y].SquarePiece == null) {
				
				//If target square is one step forward or backward, accept move
				if ((currenty - 1)== y && (currentx == x) || (currenty + 1) == y && (currentx == x)) {
					
					return true;
				}
				
			}
		//If pawn is white
		} else if (SquareArray[currentx][currenty].SquarePiece.colour == "B") {
			
			//If target square has a piece
			if (SquareArray[x][y].SquarePiece != null) {
				
				//If target square is one diagonal square away, accept move
				if ((currenty + 1 == y) && (currentx - 1) == x) {
					
					return true;
				}
				if ((currenty + 1 == y) && (currentx + 1) == x) {
					
					return true;
				}
			
			//If target square does not have a piece
			} else if (SquareArray[x][y].SquarePiece == null) {
				
				//If target square is one step forward or backward, accept move
				if ((currenty - 1)== y && (currentx == x) || (currenty + 1) == y && (currentx == x)) {
					
					return true;
				}
				
			}
		
		}
		//If it is not accepted yet, decline move
		return false;
		
	}

}
