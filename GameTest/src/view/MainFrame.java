package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import model.Player;
import controller.Fichario;
import controller.GameController;
import controller.PlayerDao;


public class MainFrame extends JFrame {
private JPanel uiMenuPanel;
private ButtonsPanel bF = new ButtonsPanel();
private UserTextPanel uTP = new UserTextPanel();
private Fichario<Player> ficharioPlayer;

    public MainFrame() throws SQLException {
        super("Main Frame");
        ficharioPlayer = new Fichario(new PlayerDao());
        bF.getPlayButton().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		uiMenuPanel.setVisible(false);
        		GamePanel gamePanel = new GamePanel();
        		getContentPane().add(gamePanel,BorderLayout.CENTER);
        		gamePanel.requestFocus();
        	}
        });
        bF.getUserDataButton().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		uiMenuPanel.setVisible(false);
           		getContentPane().add(uTP,BorderLayout.CENTER);
        		uTP.requestFocus();
        		//player.setNome(uTP.getNome().getText());
        	
        	}
        });
        
        uiMenuPanel = new JPanel();
        uiMenuPanel.setPreferredSize(new Dimension(600,600));
        uiMenuPanel.setBorder(new EmptyBorder(80,0,0,0));
        uiMenuPanel.add(bF);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(uiMenuPanel, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
        uTP.getSubmit().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(uTP.getNome().getText().isBlank() || uTP.getEmail().getText().isBlank() 
        				|| uTP.getLogin().getText().isBlank() || uTP.getPassword().getText().isBlank()) {
        			JOptionPane.showMessageDialog(null, "Preencher todos os campos");        		}
        		Player playerTemp = new Player(uTP.getNome().getText(),uTP.getLogin().getText(),uTP.getEmail().getText(),uTP.getPassword().getText());
        		ficharioPlayer.cadastrar(playerTemp);
        		uTP.setVisible(false);
        		uiMenuPanel.setVisible(true);
        	
        	}
        });
    }
    

    public static void main(String[] args) {    
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame;
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
                
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