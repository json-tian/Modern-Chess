
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

//Main Game Class
@SuppressWarnings("serial")
public class BoardGUI extends JFrame implements ActionListener {
	
	JPanel boardpanel = new JPanel();
	JPanel board = new JPanel();
	JFrame frame = new JFrame();
	
	public static JButton[][] squares = new JButton[5][7];
	public static JLabel moves = new JLabel();
	public static ImageIcon piece = new ImageIcon();
	public static Square[][] SquareArray = new Square[5][7];
	public static ImageIcon icon;
	public static JLabel turnLabel =  new JLabel();
	public static JLabel turnText = new JLabel();
	
	public static JButton backButton = new JButton();
	
	public static JLabel wTimerLabel = new JLabel();
	public static JLabel bTimerLabel = new JLabel();
	
	//Creates a timer that ticks every second
	private Timer whiteTimer = new Timer (1000, this);
	private Timer blackTimer = new Timer (1000, this);

	//Amount of time each player has
	int wTimeLeft;
	int bTimeLeft;
	
	//currentx and currenty: previous selected moves
	int currentx;
	int currenty;
	
	int numWhitePiece = 11;
	int numBlackPiece = 11;
	
	//Counter for number of moves taken
	int numMoves = 0;
	
	//Whether or not the game has ended
	boolean gameEnded = false;
	
	//True - White's Turn | False - Black's Turn
	boolean WhiteTurn = true;
	
	//Square Selection Status
	boolean selection = false;
	
	//Board Constructor
	//Game Start
	public BoardGUI() {
		
		//Object Oriented Programming
		//Source: https://docs.oracle.com/javase/tutorial/java/javaOO/objectcreation.html
		//Source: https://www3.ntu.edu.sg/home/ehchua/programming/java/J3a_OOPBasics.html
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Creating new objects (NAME, COLOUR, IMAGE LOCATION)
		Rook bRook= new Rook("bRook","B", "images/BlackRook.png");
		Rook wRook= new Rook("wRook","W", "images/WhiteRook.png");
		
		Bishop bBishop= new Bishop("bBishop","B", "images/BlackBishop.png");
		Bishop wBishop= new Bishop("wBishop","W", "images/WhiteBishop.png");
		
		Pawn wPawn = new Pawn("wPawn", "W", "images/WhitePawn.png");
		Pawn bPawn = new Pawn("bPawn", "B", "images/BlackPawn.png");
		
		Cannon wCannon = new Cannon("wCannon", "W", "images/WhiteCannon.png");
		Cannon bCannon = new Cannon("bCannon", "B", "images/BlackCannon.png");
		
		Queen wQueen = new Queen("wQueen", "W", "images/WhiteQueen.png");
		Queen bQueen = new Queen("bQueen", "B", "images/BlackQueen.png");
		
		//Setting up the frame
		frame.setSize(1200,820);
		frame.setResizable(false);
		
		//Setting frame background
		frame.setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/Background.png").getImage().getScaledInstance(1200,  820,  0))));
		frame.setLayout(null);
		
		//Setting up label to display number of moves
		moves.setText("Moves: 0");
		moves.setFont(new Font("Serif", Font.BOLD, 40));
		moves.setBounds(25, 100, 200, 100);
		frame.getContentPane().add(moves);
		
		//Retrieving selected time controls from option screen
		bTimeLeft = OptionScreen.getBlackTime();
		wTimeLeft = OptionScreen.getWhiteTime();
		
		//Setting up label to display time for black and white
		bTimerLabel.setText("Time: " + bTimeLeft);
		bTimerLabel.setFont(new Font("Serif", Font.BOLD, 40));
		bTimerLabel.setBounds(975, 200, 200, 100);
		frame.getContentPane().add(bTimerLabel);
		
		wTimerLabel.setText("Time: " + wTimeLeft);
		wTimerLabel.setFont(new Font("Serif", Font.BOLD, 40));
		wTimerLabel.setBounds(975, 400, 200, 100);
		frame.getContentPane().add(wTimerLabel);
		
		//Starting the white timer (white moves first in chess)
		whiteTimer.start();
		
		//Creating border for turnLabel
		Border turnBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY,5);
		
		//Setting up turnLabel (to display whose turn it is)
		turnLabel = new JLabel();
	    turnLabel.setBounds(60, 500, 100, 100);
	    turnLabel.setBorder(turnBorder);
	    turnLabel.setBackground(Color.WHITE);
	    turnLabel.setOpaque(true);
	    frame.getContentPane().add(turnLabel);
		
	    //Setting caption of the turnLabel
	    turnText = new JLabel();
	    turnText.setBounds(40, 430, 160, 50);
	    turnText.setOpaque(false);
	    turnText.setText("TURN:");
	    turnText.setFont(new Font("Serif", Font.BOLD, 50));
	    frame.getContentPane().add(turnText);
	    
	    //Button for returning to options screen
	    backButton = new JButton("Back");
	    backButton.setFont(new Font("Serif", Font.BOLD, 30));
	    backButton.setBounds(50,  50, 150, 50);
	    backButton.addActionListener(this);
	    frame.add(backButton);
	    
	    //Game board settings
	    //USING GRIDLAYOUT:
	    //SOURCE: https://www.tutorialspoint.com/swing/swing_gridlayout.htm
	    
	    //Setting up main board panel
		board.setLayout(new GridLayout(7,5));
		board.setSize(500, 700);
		boardpanel.add(board);
		boardpanel.setBounds(250, 0,680, 800);
		frame.add(boardpanel);
		
		Piece aPiece;
		
		//Loading game board
		for (int y = 0; y < 7; y ++) { //Number of rows 
			for (int x = 0; x < 5; x ++) { //Number of columns
				
				//Assigning pieces to their square (starting position)
				if ((x == 0 && y == 0) || (x == 4 && y == 0)) 
					aPiece=bRook;
				else if (x == 2 && y == 1) 
					 aPiece=bBishop;	
				else if ((x == 0 && y == 6) || (x == 4 && y == 6))
					aPiece=wRook;
				else if (x == 2 && y == 5)
					aPiece=wBishop;
				else if ((x == 3 && y == 0) || (x == 1 && y == 0))
					aPiece = bCannon;
				else if ((x == 3 && y == 6) || (x == 1 && y == 6))
					aPiece = wCannon;
				else if ((x == 0 && y == 1) || (x == 1 && y == 1) || (x == 3 && y == 1) || (x == 4 && y == 1) || (x == 2 && y == 2))
					aPiece = bPawn;
				else if ((x == 1 && y == 5) || (x == 3 && y == 5) || (x == 4 && y == 5) || (x == 0 && y == 5) || (x == 2 && y == 4))
					aPiece = wPawn;
				else if (x == 2 && y == 0)
					aPiece = bQueen;
				else if (x == 2 && y == 6)
					aPiece = wQueen;
				else 
					aPiece =null;
				
				//Assigning pieces to its square
				Square aSquare = new Square(x, y, aPiece);
				aSquare.SquarePiece=aPiece;
				
				//Setting square to the corresponding image
				if (aPiece != null) {
					icon = new ImageIcon(aPiece.imageName);
					aSquare.setIcon(icon);
					icon = new ImageIcon(aPiece.imageName);
					aSquare.setIcon(icon);
				}
				
				//Creating a square array to hold the squares
				SquareArray[x][y]=aSquare;  
				board.add(aSquare); 
				SquareArray[x][y].addActionListener(this);
				
				//Colouring in the squares (black and green squares)
				if ((x%2) == (y%2)) {
					
					//SOURCE: JAVA RGB: https://stackoverflow.com/questions/9698359/setbackgroundnew-color-in-java-does-not-understand-the-given-rgb-value
					SquareArray[x][y].setBackground(new Color(41, 96, 53));
				} else {
					SquareArray[x][y].setBackground(Color.WHITE);
				}
			}
		}
		
		//Setting home positions of game
		SquareArray[2][0].setBackground(Color.ORANGE);
		SquareArray[2][6].setBackground(Color.ORANGE);
		
		frame.setVisible(true);

	}
	
	//Source: PLAYING SOUND: //http://www.java2s.com/Code/JavaAPI/java.applet/AppletnewAudioClipURLaudioFileURL.htm
	private void PlaySound(File Move) {
		//Setting up sound to be played when pieces move
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Move));
			clip.start();
			
		} catch (Exception e){
		}
	}
	
	//Event handler for timer and buttons
	@Override
	public void actionPerformed(ActionEvent event) {
		//Border creation for square selection
		//ADD BORDER TO BUTTON:
		//https://docs.oracle.com/javase/7/docs/api/javax/swing/BorderFactory.html
		Border border = BorderFactory.createLineBorder(Color.GREEN,5);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		//Returning to Options Screen
		if (event.getSource() == backButton) {
			//Stopping both timers
			blackTimer.stop();
			whiteTimer.stop();
			
			//Current frame closes, optionscreen opens
			frame.dispose();
			new OptionScreen();
		}
		//Decreases a second and updates label
		if (event.getSource() == whiteTimer) {
			wTimeLeft --;
			wTimerLabel.setText("Time: " + wTimeLeft);
			
			//If there's no time left
			if (wTimeLeft == 0) {
				
				 //Opponent wins
				 JOptionPane.showMessageDialog(frame, "Black Wins!");
				 gameEnded = true;
				 blackTimer.stop();
				 whiteTimer.stop();
			}				
		//Decreases a second and updates label
		} else if (event.getSource() == blackTimer) {
			bTimeLeft --;
			bTimerLabel.setText("Time: " + bTimeLeft);
			
			//If there's no time left
			if (bTimeLeft == 0) {
				
				//Opponent wins
				 JOptionPane.showMessageDialog(frame, "White Wins!");
				 gameEnded = true;
				 blackTimer.stop();
				 whiteTimer.stop();
			}
		}
		
		//Finding clicked square
		for (int y = 0; y < 7; y ++) { //Number of rows
			for (int x = 0; x < 5; x ++) { //Number of columns
				
				//Detects which button is clicked in the array
				if (event.getSource() == SquareArray[x][y]) {
					
					//If the same button is clicked again
					if ((x == currentx) && (y == currenty)) {
						
						//If already a button selected, deselect previous button
						if (selection == true) {
							
							SquareArray[x][y].setBorder(emptyBorder);
							selection = false;
						
						//If button is not previously selected, select current button
						} else if (selection == false) {
							
							SquareArray[x][y].setBorder(border);
							selection = true;
						}
						
					} else {
						
						//If no other piece is selected, select current piece
						if (selection == false) {
							SquareArray[x][y].setBorder(border);
							selection = true;
							currentx = x;
							currenty = y;
						//If another piece is selected, validate move
						//For reference: currentx = position right now, x = new selected position
						} else if (selection == true) {
							SquareArray[currentx][currenty].setBorder(emptyBorder);
							selection = false;
							
							//If the game has not ended yet
							if (gameEnded == false) {
								//Display error message for not selecting a piece
								if (SquareArray[currentx][currenty].SquarePiece== null) {
										JOptionPane.showMessageDialog(frame, "Select a Piece!");
								
								//Check if player's turn matches their player's piece they want to move
								} else if ((SquareArray[currentx][currenty].SquarePiece.colour == "W" && WhiteTurn == true) 
										|| ((SquareArray[currentx][currenty].SquarePiece.colour == "B" && WhiteTurn == false))) {
								
									//Casting to specific piece, then call validate method
									//Source: Casting: https://www.tutorialspoint.com/java/lang/class_cast.htm
									//If the move is valid:
									if (((SquareArray[currentx][currenty].SquarePiece.getClass().cast(SquareArray[currentx][currenty].SquarePiece))
											.validateMove(SquareArray,x, y, currentx, currenty, gameEnded)==true)) { 
										
										//Getting previous piece
										Piece previousPiece = SquareArray[currentx][currenty].SquarePiece;
										
										//Updates number of pieces on board
										if (SquareArray[x][y].SquarePiece != null) {
											if (SquareArray[x][y].SquarePiece.colour == "W") {
												numWhitePiece --;
												
											} else if (SquareArray[x][y].SquarePiece.colour == "B") {
												numBlackPiece --;
											}
										}
										
										//Setting previous piece to the current square
										SquareArray[x][y].SquarePiece = previousPiece;
										
										//Playing the "move" sound
										File Move = new File("sounds/capture.wav");
										PlaySound(Move);
									
										//Setting previous image to the new square
										icon = new ImageIcon(previousPiece.imageName);
										SquareArray[x][y].setIcon(icon);
										
										//Updates turn
										if (WhiteTurn == false) {
											//Updates turn, timers, and number of moves
											WhiteTurn = true;
											numMoves++;
											blackTimer.stop();
											whiteTimer.start();
											moves.setText("Moves: " + numMoves);
											turnLabel.setBackground(Color.WHITE);
										} else if (WhiteTurn == true) {
											WhiteTurn = false;
											numMoves++;
											blackTimer.start();
											whiteTimer.stop();
											moves.setText("Moves: " + numMoves);
											turnLabel.setBackground(Color.BLACK);
										}
									
										//Setting image in previous square empty
										SquareArray[currentx][currenty].setIcon(null);
										
										//Checks if a white piece went on black's home base
										if (x == 2 && y == 0 && (SquareArray[currentx][currenty].SquarePiece.colour == "W")) {
											
											//White wins
											blackTimer.stop();
											whiteTimer.stop();
											JOptionPane.showMessageDialog(frame, "White Wins!");
											gameEnded = true;
									
										 
										//If a black piece went on white's home base
										} else if (x == 2 && y == 6 && (SquareArray[currentx][currenty].SquarePiece.colour == "B")) {
											
											//Black wins
											blackTimer.stop();
											whiteTimer.stop();
											JOptionPane.showMessageDialog(frame, "Black Wins!");
											gameEnded = true;
							
											
										} else if (numWhitePiece == 0) {
											//Black wins
											blackTimer.stop();
											whiteTimer.stop();
											JOptionPane.showMessageDialog(frame, "Black Wins!");
											gameEnded = true;
										} else if (numBlackPiece == 0) {
											//White wins
											blackTimer.stop();
											whiteTimer.stop();
											JOptionPane.showMessageDialog(frame, "White Wins!");
											gameEnded = true;
										}
										
										//After all moves finish, set previous square empty
										SquareArray[currentx][currenty].SquarePiece = null;

									}
								}
							}
						}
					}
				}
			}
		}
	}
}