//Name: Jason Tian
//
//Date: June 9th, 2017
//
//Course Code: ICS3U1-04, Instructor: Mr. Fernandes
//
//Title: Modern Chess
//
//Description: A modified spin on the classic chess games made to my own liking. It includes a different way to win the game, a smaller board,
//	adjustable time controls, and a new, unique piece! 
//
//Features: 
// - A new piece (the cannon) that moves differently from any piece in the classic chess game
// - Ability to adjust time controls for each player
// - Sounds for moving and button clicks in title screen
// - Introducing the home base! It replaces the king
//
//Major Skills:
// - A 2-Dimensional array used to handle the JButtons of the board
// - Many nested for loops for the validation of pieces
// - Many, many nested if statements for the validation of pieces
// - Object oriented programming (use of fields and inheritance)
// - GUI (frames, jpanels, jradiobuttons, jlabels, jtextareas, borders, contentpanes, imageicons, jbuttons)
// - Retrieving information between classes using getters and public variables
// - Miscellaneous (timers, sounds, actionperformed, casting, instances)
//
//Areas of Concern: 
// - Make sure to read the help screen before playing

public class ChessTest {

	public static void main(String[] args) {
		new TitleScreen();
	}

}
