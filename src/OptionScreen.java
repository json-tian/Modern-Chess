import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class OptionScreen extends JFrame implements ActionListener {
	
	//Options Screen Window
	JFrame frame = new JFrame("Options Screen");
	JPanel panel = new JPanel();
	JButton backButton = new JButton();
	JButton startButton = new JButton();
	JLabel title = new JLabel();
	ImageIcon[] images = new ImageIcon[3];
	JLabel[] labelPiece = new JLabel[3];
	JRadioButton[] wSelect = new JRadioButton[3];
	JRadioButton[] bSelect = new JRadioButton[3];
	ButtonGroup wTimes = new ButtonGroup();
	ButtonGroup bTimes = new ButtonGroup();

	//Time Length for each player
	static int whiteTime;
	static int blackTime;
	
	//Image label setup and resizing
    JLabel backLabel = (new JLabel(new ImageIcon(new ImageIcon("images/BackButton.png").getImage().getScaledInstance(125,  75,  0))));
    JLabel startLabel = (new JLabel(new ImageIcon(new ImageIcon("images/StartButton.png").getImage().getScaledInstance(225,  150,  0))));

	
	public OptionScreen() {
	
		//Setting up frame
		int width = 800;
		int height = 730;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Setting frame background
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
        title.setText("Select Time Controls");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setBounds(230, 20, 600, 100);
        panel.add(title);
        
        //Drawing each button onto frame (button setup)
        
        //Set transparent
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        
        //Set other characteristics
        backButton.setBounds(40, 40, 125, 75);
        backButton.addActionListener(this);
        backButton.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(backButton);
        
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        	
        startButton.setBounds(450, 475, 225, 150);
        startButton.addActionListener(this);
        startButton.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(startButton);
        
        startLabel.setBounds(450, 475, 225, 150);
        panel.add(startLabel);
        
        
        backLabel.setBounds(40, 40, 125, 75);
        panel.add(backLabel);
        
        //Creation of other images + resizing (for decoration)
        images[0] = new ImageIcon(new ImageIcon("images/ChessClock.png").getImage().getScaledInstance(200, 200, 0));
        images[1] = new ImageIcon(new ImageIcon("images/WhiteQueen.png").getImage().getScaledInstance(100,  100,  0));
        images[2] = new ImageIcon(new ImageIcon("images/BlackQueen.png").getImage().getScaledInstance(100,  100,  0));
        
        
        for (int i = 0; i < 3; i ++) {
        	//Sets location, adds image to label, and adds to panel
        	labelPiece[i] = new JLabel(images[i]);
        	panel.add(labelPiece[i]);
      	
        }
        
        //Sets label location
        labelPiece[0].setBounds(80, 455, 200, 200);
        labelPiece[1].setBounds(150, 120, 100, 100);
        labelPiece[2].setBounds(550, 120, 100, 100);
        
        
        //JRADIOBUTTONS USAGE: http://www.codejava.net/java-se/swing/jradiobutton-basic-tutorial-and-examples
        
        for (int i = 0; i < 3; i ++) {
        	
        	wSelect[i] = new JRadioButton();
        	bSelect[i] = new JRadioButton();
        	
        	//Sets location for radio buttons
        	wSelect[i].setBounds(100, 200 + (60 * i), 220, 100);
        	bSelect[i].setBounds(500, 200 + (60 * i), 220, 100);
        	
        	//Removes radio button background
        	wSelect[i].setOpaque(false);
        	bSelect[i].setOpaque(false);
        	
        	//Font characterisation for radio buttons
        	wSelect[i].setFont(new Font("Monospaced", 3, 30));
        	bSelect[i].setFont(new Font("Monospaced", 3, 30));
        	
        	//SOURCE: GROUPING: https://coderanch.com/t/334519/java/set-default-radio-button
        	//Groups the buttons together
        	wTimes.add(wSelect[i]);
        	bTimes.add(bSelect[i]);
        	
        	panel.add(wSelect[i]);
        	panel.add(bSelect[i]);
        	
        	
        }
        //Setting default values
        wSelect[2].setSelected(true);
        bSelect[2].setSelected(true);
        
        //Setting text for radio buttons
        wSelect[0].setText("10 Seconds");
        wSelect[1].setText("1 Minute");
        wSelect[2].setText("5 Minutes");
        
        bSelect[0].setText("10 Seconds");
        bSelect[1].setText("1 Minute");
        bSelect[2].setText("5 Minutes");
        
        frame.setVisible(true);
	}
	
	//Event handler for buttons
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//Closes current frame
		frame.setVisible(false);
		
			if (event.getSource()== startButton) {
				//If start button is clicked, give players time based on radio button selected
				if (wSelect[0].isSelected())
					whiteTime = 10;
				if (wSelect[1].isSelected())
					whiteTime = 60;
				if (wSelect[2].isSelected())
					whiteTime = 300;
				if (bSelect[0].isSelected())
					blackTime = 10;
				if (bSelect[1].isSelected())
					blackTime = 60;
				if (bSelect[2].isSelected())
					blackTime = 300;
				
				new BoardGUI();
				
			} else if (event.getSource() == backButton) {
				
				new TitleScreen();
				
			}

		}

	public static int getWhiteTime() {
		return whiteTime;
	}

	public static int getBlackTime() {
		return blackTime;
	}

	}