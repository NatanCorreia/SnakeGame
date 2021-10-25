package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import controller.GameController;


public class MainFrame extends JFrame {

    public MainFrame() {
        super("Main Frame");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(new GamePanel(), BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {    
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });     
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final Dimension PANEL_SIZE = new Dimension(600, 600);
    

    private Timer timer = new Timer(GameController.GAME_SPEED, this);
    private GameRenderer renderer = new GameRenderer(PANEL_SIZE);
    private GameController controller = new GameController();

    public GamePanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        controller.init();
        timer.start();      
    }

    public Dimension getPreferredSize() {
        return PANEL_SIZE;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(controller.getCurrentFrame().getSnek().isSnekIsAlive()) {
	        controller.generateNextFrame();
	        renderer.renderFrame(controller.getCurrentFrame(), g);
        }else {
        	renderer.drawEndGame(controller.getCurrentFrame(), g);
        	timer.stop();
        	
        	String usr =JOptionPane.showInputDialog("Digite seu Nome:");    	       	  
        }
        
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        controller.processKeyEvent(e);
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}