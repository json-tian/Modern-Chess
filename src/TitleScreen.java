import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;

@SuppressWarnings("serial")
public class TitleScreen extends JFrame implements ActionListener {
	
	JFrame frame = new JFrame("JASON - MODERN CHESS");
	JPanel panel = new JPanel();
	JButton [] buttons = new JButton[3];
	JLabel title = new JLabel();
	
	//ImageLabels declaration + resizing them to fit
	JLabel titleImage = (new JLabel(new ImageIcon(new ImageIcon("images/Title.png").getImage().getScaledInstance(450, 100, 0))));
	JLabel playLabel = (new JLabel(new ImageIcon(new ImageIcon("images/PlayButton.png").getImage().getScaledInstance(150, 100, 0))));
    JLabel helpLabel = (new JLabel(new ImageIcon(new ImageIcon("images/HelpButton.png").getImage().getScaledInstance(150, 100,  0))));
    JLabel exitLabel = (new JLabel(new ImageIcon(new ImageIcon("images/ExitButton.png").getImage().getScaledInstance(100,  50,  0))));
    JLabel cannonLabel1 = (new JLabel(new ImageIcon(new ImageIcon("images/blackCannon.png").getImage().getScaledInstance(100,  100,  0))));
    JLabel cannonLabel2 = (new JLabel(new ImageIcon(new ImageIcon("images/blackCannon.png").getImage().getScaledInstance(100,  100,  0))));
	
	public TitleScreen() {
	
		//Setting up frame
		int width = 800;
		int height = 730;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Drawing frame background
        frame.setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/TitleBackground.png").getImage().getScaledInstance(width,  height,  0))));
        frame.setLayout(null);
       
        frame.setSize(width, height);
        frame.setResizable(false);
        
        //Setting up main panel
        panel.setLayout(null);
        panel.setSize(800, 730);
        panel.setOpaque(false);
        
        frame.add(panel);
        
        //Sets up title
        title.setText("CANNONS INSTEAD OF KNIGHTS!");
        title.setFont(new Font("Monospaced", Font.BOLD, 30));
        title.setBounds(200, 180, 600, 50);
        panel.add(title);
        
        //For self reference
        //buttons[0] = play button
        //buttons[1] = help button
        //buttons[2] = exit button
        
        //Drawing each button onto frame (button setup)
        for (int u = 0; u < 3; u ++) {
        	
        	buttons[u] = new JButton();
        	
        	//Making buttons transparent
        	buttons[u].setOpaque(false);
        	buttons[u].setContentAreaFilled(false);
        	buttons[u].setBorderPainted(false);
        	
        	//Setting button characteristics
        	buttons[u].setBounds(175 + (300 * u), 400, 150, 100);
        	buttons[u].addActionListener(this);
        	buttons[u].setFont(new Font("Serif", Font.BOLD, 50));
        	panel.add(buttons[u]);
        }
        
        //re-adjusting the button
        buttons[2].setBounds(50, 50, 100, 50);
        buttons[2].setFont(new Font("Serif", Font.BOLD, 30));
        
        //Drawing the imagelabels to their location
        playLabel.setBounds(175, 400, 150, 100);
        panel.add(playLabel);
        
        helpLabel.setBounds(475, 400, 150, 100);
        panel.add(helpLabel);
        
        exitLabel.setBounds(50, 50, 100, 50);
        panel.add(exitLabel);
        
        titleImage.setBounds(130, 80, 600, 100);
        panel.add(titleImage);
        
        cannonLabel1.setBounds(80, 120, 100, 100);
        panel.add(cannonLabel1);
        
        cannonLabel2.setBounds(680, 120, 100, 100);
        panel.add(cannonLabel2);
        
        frame.setVisible(true);
	}
	
	//Event handler for buttons
	@Override
	public void actionPerformed(ActionEvent event) {		
		//Plays the click sound
		File Click = new File("sounds/click.wav");
		PlaySound(Click);
		
		//Closes current frame
		frame.setVisible(false);
		
			//If a certain button is clicked
			if (event.getSource()== buttons[0]) {
				new OptionScreen();
			} else if  (event.getSource()== buttons[1]) {
				new HelpScreen();
			} else if (event.getSource() == buttons[2]) {
				System.exit(ABORT);
			}

		} 
	
	//Play Sound
	private void PlaySound(File Click) {
		//Source: PLAYING SOUND: //http://www.java2s.com/Code/JavaAPI/java.applet/AppletnewAudioClipURLaudioFileURL.htm
		//Sets up click sounds for buttons
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Click));
			clip.start();			
		} catch (Exception e){	
		}		
	}
}