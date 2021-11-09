package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Fichario;
import controller.GameController;
import controller.PlayerDao;
import controller.PlayerTM;
import model.Player;

public class MainFrame extends JFrame {
	private JPanel uiMenuPanel, uiDPanel;
	public static int PLAYER_ID;
	public static String FILE_NAME;
	public static boolean Load;
	private ButtonsPanel bP = new ButtonsPanel();
	private DifficultyPanel dP = new DifficultyPanel();
	private UserTextPanel uTP = new UserTextPanel();
	private Fichario<Player> ficharioPlayer = new Fichario(new PlayerDao());
	private ArrayList<Player> players = new ArrayList();
	private RankingPanel rP = new RankingPanel();
	private PlayerTM pTM ;
	private JFileChooser chooser = new JFileChooser("C:\\Users\\Natan Correia\\git\\SnakeGame2\\GameTest\\res");

	public MainFrame() throws SQLException {
		super("SNAKE GAME");

		bP.getPlayButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiMenuPanel.setVisible(false);
				uiDPanel = new JPanel();
				uiDPanel.setPreferredSize(new Dimension(600, 600));
				uiDPanel.setBorder(new EmptyBorder(80, 0, 0, 0));
				uiDPanel.add(dP);
				getContentPane().add(uiDPanel, BorderLayout.CENTER);
				dP.getEasyButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						uiDPanel.setVisible(false);
						GameController.GAME_SPEED = 100;
						GamePanel gamePanel;
						try {
							gamePanel = new GamePanel();
							getContentPane().add(gamePanel, BorderLayout.CENTER);
							gamePanel.requestFocus();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				});
				dP.getNormalButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						uiDPanel.setVisible(false);
						GameController.GAME_SPEED = 70;
						GamePanel gamePanel;
						try {
							gamePanel = new GamePanel();
							getContentPane().add(gamePanel, BorderLayout.CENTER);
							gamePanel.requestFocus();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				});
				dP.getHardButton().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						uiDPanel.setVisible(false);
						GameController.GAME_SPEED = 40;
						GamePanel gamePanel;
						try {
							gamePanel = new GamePanel();
							getContentPane().add(gamePanel, BorderLayout.CENTER);
							gamePanel.requestFocus();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				});

			}
		});
		bP.getUserDataButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiMenuPanel.setVisible(false);
				getContentPane().add(uTP, BorderLayout.CENTER);
				uTP.requestFocus();

			}
		});
		bP.getLoadButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose a txt file:", "txt");
				chooser.setFileFilter(filter);
				int retorno = chooser.showOpenDialog(null);

			
			
				if (retorno == JFileChooser.APPROVE_OPTION) {
					MainFrame.FILE_NAME = "Natan";
					MainFrame.Load = true;
					
				}
				//uiMenuPanel.setVisible(true);
				GamePanel gamePanel;
				
				try {
					gamePanel = new GamePanel();
					uiMenuPanel.setVisible(false);
					getContentPane().add(gamePanel, BorderLayout.CENTER);
					//getContentPane().remove(uiMenuPanel);
					gamePanel.requestFocus();
				
					
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		bP.getRankButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiMenuPanel.setVisible(false);
				String [] colunas = {"Nome","Email","Login","Score"};
				players = ficharioPlayer.buscarTudo();
				pTM = new PlayerTM(players,colunas);
				System.out.println(players.get(1).getScore());
				rP.getPlayersTable().setModel(pTM);
				getContentPane().add(rP, BorderLayout.CENTER);
				rP.requestFocus();

			}
		});
		uiMenuPanel = new JPanel();
		uiMenuPanel.setPreferredSize(new Dimension(600, 600));
		uiMenuPanel.setBorder(new EmptyBorder(80, 0, 0, 0));
		uiMenuPanel.add(bP);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(uiMenuPanel, BorderLayout.CENTER);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);

		uTP.getSubmit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uTP.getNome().getText().isBlank() || uTP.getEmail().getText().isBlank()
						|| uTP.getLogin().getText().isBlank() || uTP.getPassword().getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencher todos os campos");
				}
				Player playerTemp = new Player(uTP.getNome().getText(), uTP.getLogin().getText(),
						uTP.getEmail().getText(), uTP.getPassword().getText());
				ficharioPlayer.cadastrar(playerTemp);
				MainFrame.PLAYER_ID = playerTemp.getId();
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

	private Timer timer;
	private GameRenderer renderer = new GameRenderer(PANEL_SIZE);
	private GameController controller = new GameController();
	private Fichario<Player> ficharioPlayer = new Fichario(new PlayerDao());	

	public GamePanel() throws SQLException {
		addKeyListener(this);
		setFocusable(true);
		setVisible(true);
		setFocusTraversalKeysEnabled(false);
		controller.init();
		requestFocus();
		timer = new Timer(GameController.GAME_SPEED, this);
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
		if (controller.getCurrentFrame().getSnek().isSnekIsAlive()) {
			controller.generateNextFrame();
			renderer.renderFrame(controller.getCurrentFrame(), g);
		} else {
			renderer.drawEndGame(controller.getCurrentFrame(), g);
			ficharioPlayer.atualizarScore(MainFrame.PLAYER_ID, controller.getCurrentFrame().getScore());
			timer.stop();

		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

}