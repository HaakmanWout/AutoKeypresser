package net;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Main extends JFrame {
	
	/**	
	 * @author Wout Haakman 
	 * 
	 * Auto Key Presser.
	 * 
	 * This little software application is used to use for AFK purposes, whether it is to make homework or automatically enter a chain of forms without to much effort.
	 * You can read a book in the mean time while Auto Keypresser does the job for you. Main use is for repetitive actions.
	 * 
	 * For any questions or recommendations you can reach me through these addresses:
	 * E-mail:		wouthaakman@hotmail.com
	 * Github:		HaakmanWout
	 * Reddit:		u/haakmanwout
	 * 
	 */
	
    private static final long serialVersionUID = 1L;
    private static boolean loop = false;
    private static String keyset = "E:ctrl,E:v,D:ctrl,D:v,W:250,E:enter,D:enter,W:250,E:enter,D:enter";
    
    /*
     * Class method called 'Main', being called by the actual Main method. Mustn't be too confusing right?
     * Throws general Exception so I don't have to deal with that too many times.
     */
    public Main() throws Exception {
    	/*
    	 * Sets the look and feel of the UI to the standard OS look and feel for a more native, less java shitty experience.
    	 */
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        /*
         * The 'Close' Swing button, to manually close Auto Keypresser. 
         * Still debating wether I should delete it since it has no real use. 
         * I feel like using the standard closing button is more intuitive than pressing the close button.
         */
        JButton close = new JButton("Close");
        close.setToolTipText("Close Auto Keypresser");
        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        /*
         * The 'Start' Swing button, used to start the automatic keypressing loop.
         */
        JButton start = new JButton("Start");
        start.setToolTipText("Start automatic keypressing loop");
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loop = true;
            }
        });

        /*
         * The 'Stop' Swing button, used to stop the automatic keypressing loop.
         */
        JButton stop = new JButton("Stop");
        stop.setToolTipText("Stop automatic keypressing loop");
        stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                loop = false;
            }
        });
        
        /*
         * The 'Change Keys' Swing button, used to change the key preset for the automatic keypressing loop.
         * 
         * When the button is pressed it shows the input dialog, showing the current key set loop, the format for putting in custom key set and 'Apply' and 'Cancel' button.
         */
        JButton changeKeys = new JButton("Change keys");
        changeKeys.setToolTipText("Edit the automatic keypressing loop to specify the keys that have to be pressed");
        changeKeys.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		String newKeyset = JOptionPane.showInputDialog(null, "Current key set:    " + keyset + System.lineSeparator() + 
        															 "E:      -> enable key" + System.lineSeparator() + "D:     -> disable key" + System.lineSeparator() +
        															 "W:    -> wait in ms" + System.lineSeparator() + ",        -> break to next key", "Change key set", JOptionPane.PLAIN_MESSAGE);
        		if(newKeyset != null && newKeyset.length() >= 2)
        			keyset = newKeyset;
        	}
        });
        
        /*
         * The 'Info' Swing text area, displays information for the user to assist with using the software.
         */
        JTextArea info = new JTextArea("To use Auto Keypresser for your own purposes," + System.lineSeparator() + 
        							   "please click on 'Change keys' to configure the key chain." + System.lineSeparator() + "To start or stop the Auto Keypresser, press the homonymous button");
        info.setEditable(false);
        
        /*
         * All of this below is used to designate the Swing UI components to a predefined position.
         * I used BoxLayout so I can stack the components on top and next to each other for a better flow.
         */
        Box top = Box.createHorizontalBox();
        top.add(info);
        
        Box bottom = Box.createHorizontalBox();
        bottom.add(new JLabel("Auto Keypresser"));
        bottom.add(Box.createRigidArea(new Dimension(10, 40)));
        bottom.add(close);
        bottom.add(Box.createRigidArea(new Dimension(10, 40)));
        bottom.add(start);
        bottom.add(Box.createRigidArea(new Dimension(10, 40)));
        bottom.add(stop);
        bottom.add(Box.createRigidArea(new Dimension(10, 40)));
        bottom.add(changeKeys);
    
        Box left = Box.createVerticalBox();
        left.add(top);
        left.add(bottom);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(left, BorderLayout.CENTER);
        
        /*
         * Initializing the JFrame that this Class inherites.
         */
        add(panel);
        setTitle("Auto Keypresser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
        setVisible(true);
        setAlwaysOnTop(true);
        
        /*
         * Using the Robot class to control Keyboard input.
         * Sleeping the loop so any software doens't overflow and can react to the input that it takes in from Auto Keypresser.
         */
        Robot robot = new Robot();
        while(true){
            if(loop){
            	String[] keyArray = keyset.split(",");
            	for(int i = 0; i < keyArray.length; i++){
            		String[] dubs = keyArray[i].split(":");
            		if(dubs[0].equals("E")){
            			robot.keyPress(getKey(dubs[1]));
            		}else if(dubs[0].equals("D")){
            			robot.keyRelease(getKey(dubs[1]));
            		}else if(dubs[0].equals("W")){
            			Thread.sleep(Long.parseLong(dubs[1]));
            		}
            	}
            } 
            Thread.sleep(250); 
        }
    }
    
    /*
     * Method to convert a string to a KeyEvent integer.
     * I hate myself for implementing this, there must be a better way to approach all of this
     */
    
    //I NEED TO THINK OF SOMETHING BETTER
    private int getKey(String key){
    	int keyEvent = KeyEvent.VK_E;
    	switch(key){
    		case "ctrl":	keyEvent = KeyEvent.VK_CONTROL;
    						break;
    		case "enter":	keyEvent = KeyEvent.VK_ENTER;
							break;
    		case "tab":		keyEvent = KeyEvent.VK_TAB;
							break;
    		case "backspace": keyEvent = KeyEvent.VK_BACK_SPACE;
    						break;
    		case "caps lock": keyEvent = KeyEvent.VK_CAPS_LOCK;
    						break;
    		case "semicolon": keyEvent = KeyEvent.VK_SEMICOLON;
    						break;
    		case "up":		keyEvent = KeyEvent.VK_UP;
    						break;
    		case "down":	keyEvent = KeyEvent.VK_DOWN;
							break;
    		case "left":	keyEvent = KeyEvent.VK_LEFT;
							break;
    		case "right":	keyEvent = KeyEvent.VK_RIGHT;
							break;
    		case "0":		keyEvent = KeyEvent.VK_0;
							break;
    		case "1":		keyEvent = KeyEvent.VK_1;
							break;
    		case "2":		keyEvent = KeyEvent.VK_2;
							break;
    		case "3":		keyEvent = KeyEvent.VK_3;
							break;
    		case "4":		keyEvent = KeyEvent.VK_4;
							break;
    		case "5":		keyEvent = KeyEvent.VK_5;
							break;
    		case "6":		keyEvent = KeyEvent.VK_6;
							break;
    		case "7":		keyEvent = KeyEvent.VK_7;
							break;
    		case "8":		keyEvent = KeyEvent.VK_8;
							break;
    		case "9":		keyEvent = KeyEvent.VK_9;
							break;
    		case "a":		keyEvent = KeyEvent.VK_A;
							break;
    		case "b":		keyEvent = KeyEvent.VK_B;
							break;
    		case "c":		keyEvent = KeyEvent.VK_C;
							break;
    		case "d":		keyEvent = KeyEvent.VK_D;
							break;
    		case "e":		keyEvent = KeyEvent.VK_E;
							break;
    		case "f":		keyEvent = KeyEvent.VK_F;
							break;
    		case "g":		keyEvent = KeyEvent.VK_G;
							break;
    		case "h":		keyEvent = KeyEvent.VK_H;
							break;
    		case "i":		keyEvent = KeyEvent.VK_I;
							break;
    		case "j":		keyEvent = KeyEvent.VK_J;
							break;
    		case "k":		keyEvent = KeyEvent.VK_K;
							break;
    		case "l":		keyEvent = KeyEvent.VK_L;
							break;
    		case "m":		keyEvent = KeyEvent.VK_M;
							break;
    		case "n":		keyEvent = KeyEvent.VK_N;
							break;
    		case "o":		keyEvent = KeyEvent.VK_O;
							break;
    		case "p":		keyEvent = KeyEvent.VK_P;
							break;
    		case "q":		keyEvent = KeyEvent.VK_Q;
							break;
    		case "r":		keyEvent = KeyEvent.VK_R;
							break;
    		case "s":		keyEvent = KeyEvent.VK_S;
							break;
    		case "t":		keyEvent = KeyEvent.VK_T;
							break;
    		case "u":		keyEvent = KeyEvent.VK_U;
							break;
    		case "v":		keyEvent = KeyEvent.VK_V;
    						break;
    		case "w":		keyEvent = KeyEvent.VK_W;
							break;
    		case "x":		keyEvent = KeyEvent.VK_X;
    						break;
    		case "y":		keyEvent = KeyEvent.VK_Y;
							break;
    		case "z":		keyEvent = KeyEvent.VK_Z;
							break;
    		default:		keyEvent = KeyEvent.VK_CONTROL;
    						break;
    	}
    	
    	return keyEvent;
    }
    
    /*
     * The main function/method, opens the 'Main' method from the 'Main' class. Since 'Main' throws an Exception, it has to take in an Exception e.
     */
    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
}
