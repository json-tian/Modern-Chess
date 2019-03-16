
//Parent class to be inherited
public class Piece {
	
	//Piece properties: colour, name, and image location
	public String colour;
	public String name;
	public String imageName;
    
	//Method to be overridden
	public boolean validateMove(Square[][] SquareArray,int x, int y, int currentx, int currenty, boolean gameEnded){
		return false;
	}
}
