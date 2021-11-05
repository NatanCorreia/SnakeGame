package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import controller.GameController;

public class GameP extends JPanel implements ActionListener, KeyListener {
    private static final Dimension PANEL_SIZE = new Dimension(600, 600);
    

    private Timer timer = new Timer(GameController.GAME_SPEED, this);
    private GameRenderer renderer = new GameRenderer(PANEL_SIZE);
    private GameController controller = new GameController();

    public GameP() {
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
        paintGame(g);
        
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        controller.processKeyEvent(e);
    }
    public void paintGame(Graphics g) {
    	  if(controller.getCurrentFrame().getSnek().isSnekIsAlive()) {
  	        controller.generateNextFrame();
  	        renderer.renderFrame(controller.getCurrentFrame(), g);
          }else {
          	renderer.drawEndGame(controller.getCurrentFrame(), g);
          	timer.stop();
          	
          	   	       	  
          }
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
