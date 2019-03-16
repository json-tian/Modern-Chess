
public class Cannon extends Piece {

	public Cannon(String name, String colour, String imageName) {
		//Assigns a name, colour, and image location
		this.name = name;
		this.colour = colour;
		this.imageName = imageName;
		
	}

	//Validates piece move
	public boolean validateMove(Square[][] SquareArray, int x, int y, int currentx, int currenty, boolean gameEnded) {
		
		//To count number of pieces in between piece position and selected position
		int piecesBlocked = 0;
		
		//If game has ended, decline move
		if (gameEnded == true)
			return false;
		
		//If new position has a piece, then it must be a capture move
		if (SquareArray[x][y].SquarePiece != null) {
			
			//If piece in new position is its own colour, decline move
			if (SquareArray[currentx][currenty].SquarePiece.colour == SquareArray[x][y].SquarePiece.colour) {
				return false;
		
			//If piece in new position is NOT its own colour
			} else if (SquareArray[currentx][currenty].SquarePiece.colour != SquareArray[x][y].SquarePiece.colour) {
				
				//Checks if only x or y changes
				if ((x == currentx) || (y == currenty)) {
					
					if (x == currentx) {
						
						if (y > currenty) {
							
							//Moving Down
							for (int i = (y-1); i > currenty; i --) {
								
								//Counts # of pieces in between
								if (SquareArray[x][i].SquarePiece != null) 
									
									piecesBlocked ++;
								
							}
						
						} else if (y < currenty) {
							
							//Moving Up
							for (int i = (y+1); i < currenty; i ++) {
								//Counts # of pieces in between
								if (SquareArray[x][i].SquarePiece != null)
						
									piecesBlocked ++;
								
							}
						}
					} else if (y == currenty) {
						
						if (x > currentx) {
							
							//Moving Right
							for (int i = (x - 1); i > (currentx); i --) {
								//Counts # of pieces in between
								if (SquareArray[i][y].SquarePiece != null)
									
									piecesBlocked ++;
							}
							
						} else if (x < currentx) {
							
							//Moving Left
							for (int i = (x+1); i < currentx; i ++) {
								//Counts # of pieces in between
								if (SquareArray[i][y].SquarePiece != null)	
									piecesBlocked ++;
							}
						}
					}
				}
			}
			//If there isn't one piece in the way, decline move
			if (piecesBlocked != 1)
				return false;
			
			//If it is not capturing piece (moving like the rook)
			} else if (SquareArray[x][y].SquarePiece == null) {				
				if ((x == currentx) || (y == currenty)) {					
					if (x == currentx) {						
						if (y > currenty) {		
							
							//Moving Down
							for (int i = (y-1); i > currenty; i --) {
								//If piece is in between, decline move
								if (SquareArray[x][i].SquarePiece != null) 									
									return false;								
							}						
						} else if (y < currenty) {	
							
							//Moving Up
							for (int i = (y+1); i < currenty; i ++) {
								//If piece is in between, decline move
								if (SquareArray[x][i].SquarePiece != null)						
									return false;								
							}
						}
					} else if (y == currenty) {						
						if (x > currentx) {
							
							//Moving Right
							for (int i = (x - 1); i > (currentx); i --) {
								//If piece is in between, decline move
								if (SquareArray[i][y].SquarePiece != null)									
									return false;	
							}							
						} else if (x < currentx) {							
							//Moving Left
							for (int i = (x+1); i < currentx; i ++) {
								//If piece is in between, decline move
								if (SquareArray[i][y].SquarePiece != null)
									return false;
							}
						}						
					}
					//If not declined yet, move is valid!
					return true;
				}
				//If it does not move or capture, decline move (from if statements above)
				return false;
			}
			//If it captures correctly, move is valid!
			return true;
		}
	}