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
        start.setToolTipText("Start automatic keypress loop");
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
        stop.setToolTipText("Stop automatic keypress loop");
        stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                loop = false;
            }
        });
        
        /*
         * The 'Info' Swing text area, displays information for the user to assist with using the software.
         */
        JTextArea info = new JTextArea("To use Auto Keypresser for your own purposes," + System.lineSeparator() + "please click on 'Change keys' to configure the key chain." + 
        									System.lineSeparator() + "To start or stop the Auto Keypresser, press the homonymous button");
        info.setToolTipText("Information");
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
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                    Thread.sleep(250);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_V);
                    Thread.sleep(250);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } 
            Thread.sleep(250); 
        }
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
