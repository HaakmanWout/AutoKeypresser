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
    
    public Main() throws Exception {
    	
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        
        JButton close = new JButton("Close");
        close.setToolTipText("Close Auto Keypresser");
        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        JButton start = new JButton("Start");
        start.setToolTipText("Start automatic keypress loop");
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loop = true;
            }
        });

        JButton stop = new JButton("Stop");
        stop.setToolTipText("Stop automatic keypress loop");
        stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                loop = false;
            }
        });
        
        JTextArea info = new JTextArea("To use Auto Keypresser for your own purposes," + System.lineSeparator() + "please click on 'Change keys' to configure the key chain.");
        info.setToolTipText("Information");
        info.setEditable(false);
        
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
        
        add(panel);
        setTitle("Auto Keypresser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
        setVisible(true);
        setAlwaysOnTop(true);
        
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

    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
}
