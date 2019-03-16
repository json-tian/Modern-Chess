import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class HelpScreen extends JFrame implements ActionListener {
	
	//Title Screen Window
	JFrame frame = new JFrame("Help Screen");
	JPanel panel = new JPanel();
	JButton buttons = new JButton();
	JLabel title = new JLabel();
	ImageIcon[] piece = new ImageIcon[5];
	JTextArea[] description = new JTextArea[7];
	
	//Labels holding the imageicons
	JLabel[] labelPiece = new JLabel[6];
	
    JLabel backLabel = (new JLabel(new ImageIcon(new ImageIcon("images/BackButton.png").getImage().getScaledInstance(125,  75,  0))));
	
	public HelpScreen() {
	
		//Setting up frame
		int width = 800;
		int height = 730;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Drawing frame background
        frame.setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/Background.png").getImage().getScaledInstance(width,  height,  0))));
        frame.setLayout(null);
       
        frame.setSize(width, height);
        frame.setResizable(false);
        
        //Main panel setup
        panel.setLayout(null);
        panel.setSize(800, 730);
        panel.setOpaque(false);
        
        frame.add(panel);
        
        //Sets up title
        title.setText("How to Play");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Serif", Font.BOLD, 60));
        title.setBounds(230, 20, 600, 100);
        panel.add(title);
        
        //Drawing each button onto frame (button setup)
        //Set buttons transparent
        buttons.setOpaque(false);
        buttons.setContentAreaFilled(false);
        buttons.setBorderPainted(false);
        
        //Setting button characteristics
        buttons.setBounds(40, 40, 125, 75);
        buttons.addActionListener(this);
        buttons.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(buttons);
        
        backLabel.setBounds(40, 40, 125, 75);
        panel.add(backLabel);
        
        //Creation of imageicons + resizing
        piece[0] = new ImageIcon(new ImageIcon("images/WhiteCannon.png").getImage().getScaledInstance(70, 70, 0));
        piece[1] = new ImageIcon(new ImageIcon("images/WhitePawn.png").getImage().getScaledInstance(70,  70,  0));
        piece[2] = new ImageIcon(new ImageIcon("images/WhiteQueen.png").getImage().getScaledInstance(70,  70,  0));
        piece[3] = new ImageIcon(new ImageIcon("images/WhiteRook.png").getImage().getScaledInstance(70,  70,  0));
        piece[4] = new ImageIcon(new ImageIcon("images/WhiteBishop.png").getImage().getScaledInstance(70,  70,  0));
        
        //Help Screen Content
        for (int i = 0; i < 7; i ++) {
        	
        	//Creates textarea and its properties
        	description[i] = new JTextArea();
        	description[i].setFont(new Font("Monospaced", 3 ,15));
        	description[i].setBounds(100, (275 + (80 * i)), 600, 70);
        	description[i].setLineWrap(true);
        	description[i].setWrapStyleWord(true);
        	description[i].setBackground(Color.LIGHT_GRAY);
        	description[i].setEditable(false);
        	panel.add(description[i]);
        }
        
        
        for (int i = 0; i < 5; i ++) {
        	//Assigns location and image to each label
        	labelPiece[i] = new JLabel(piece[i]);
        	
        	labelPiece[i].setBounds(30, (275 + (80 * i)), 70, 70);
        	panel.add(labelPiece[i]);
        	
        }
        
        //Creates a black border for home piece
        Border border = BorderFactory.createLineBorder(Color.BLACK,5);
        
        //Setting description text and adjusting location of the description
        description[5].setText("This is the most important square in the whole game! If an enemy piece lands on this square, it's GAME OVER! Cannot be moved!");
        description[5].setBounds(100, 200, 600, 50);
        
        description[6].setText("Welcome to Modern Chess! In this game, 2 players take turns to get to the opponent's base! First one there wins!"); 
        description[6].setBounds(100, 130, 600, 50);
        
        description[0].setText("THIS IS THE CANNON: This piece is different from all the others! It moves like a rook, but it captures by jumping over another piece!");
        description[1].setText("This is the pawn: The soldiers of the battlefield! They can move one step at a time back or forwrds and captures diagonally!");
        description[2].setText("This is the queen: The most powerful piece! It can move in a cross (like the rook) and diagonally (like the bishop)!");
        description[3].setText("This is the rook: This moves in the shape of a cross; up, down, left, and right!");
        description[4].setText("This is the bishop: This moves diagonally; up-right, up-left, down-left, and down-right!");
        
        //Drawing the unique homebase icon
        labelPiece[5] = new JLabel();
        labelPiece[5].setBounds(40, 200, 50, 50);
        labelPiece[5].setBorder(border);
        labelPiece[5].setBackground(Color.ORANGE);
        labelPiece[5].setOpaque(true);
        panel.add(labelPiece[5]);
        
        
        frame.setVisible(true);
	}
	
	//Event Handler for button
	@Override
	public void actionPerformed(ActionEvent event) {
		//Closes current frame
		frame.setVisible(false);
			if (event.getSource()== buttons) {
				new TitleScreen();
			}

		} 
	}