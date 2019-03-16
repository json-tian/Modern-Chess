import javax.swing.JButton;

//Usage of inheritance and all about Object Oriented Programming
//Source: http://beginnersbook.com/2013/04/oops-concepts/
@SuppressWarnings("serial")
public class Square extends JButton {

	public Piece SquarePiece;
	public int xCoord;
	public int yCoord;

	//Constructor
	public Square(int x, int y,Piece type ) {
		
		//Assigning each square a coordinate and piece type
		this.xCoord=x;
		this.yCoord=y;
			
		if (type!=null)
			this.SquarePiece=type;
	}
}
