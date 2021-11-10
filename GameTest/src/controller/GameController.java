package controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import helpers.LoadSave;
import model.Apple;
import model.Banana;
import model.EasyObstacle;
import model.GameFrame;
import model.GameMap;
import model.Goal;
import model.HardObstacle;
import model.NormalObstacle;
import model.Obstacle;
import model.Player;
import model.PlayerStatus;
import model.Rock;
import model.Segment;
import model.Snek;
import view.MainFrame;

public class GameController {

	private GameFrame currentFrame = null;
	public static int GAME_SPEED;
	private Random random;
	private int timePassed = 0, timePassedObs = 0;
	private ArrayList<Point> pontos;
	private LoadSave lS = new LoadSave();
	private Fichario<Player> ficharioPlayer;
	private PlayerStatus ps;
	private boolean grow = true;

	public void init() throws SQLException {
		initClasses();
		currentFrame.checkDifficulty();
		if (MainFrame.Load) {
			currentFrame = lS.processAndLoad(MainFrame.FILE_NAME, currentFrame);

			GameController.GAME_SPEED = currentFrame.getGameDifficulty();
			MainFrame.PLAYER_ID = currentFrame.getPlayerId();

		}

	}

	public void processKeyEvent(KeyEvent evt) {
		int code = evt.getKeyCode();
		lS = new LoadSave();
		if (code == KeyEvent.VK_UP) {
			if (currentFrame.getSnek().getDirection().y != 1)
				currentFrame.getSnek().setDirection(new Point(0, -1));
		} else if (code == KeyEvent.VK_RIGHT) {
			if (currentFrame.getSnek().getDirection().x != -1)
				currentFrame.getSnek().setDirection(new Point(1, 0));
		} else if (code == KeyEvent.VK_LEFT) {
			if (currentFrame.getSnek().getDirection().x != 1)
				currentFrame.getSnek().setDirection(new Point(-1, 0));
		} else if (code == KeyEvent.VK_DOWN) {
			if (currentFrame.getSnek().getDirection().y != -1)
				currentFrame.getSnek().setDirection(new Point(0, 1));
		} else if (code == KeyEvent.VK_SPACE && currentFrame.isPausou()) {

			currentFrame.setPausou(false);
		} else if (code == KeyEvent.VK_SPACE) {

			currentFrame.setPausou(true);
		} else if ((code == KeyEvent.VK_S) && evt.isControlDown()) {
			lS.processAndWrite(JOptionPane.showInputDialog("Type the name of the save: "), currentFrame);
		}
	}

	public void generateNextFrame() {

		if (currentFrame == null) {
			throw new IllegalStateException("current frame is null. Did you call 'init'?");
		}

		if (currentFrame.getSnek().isSnekIsAlive() && !currentFrame.isPausou()) {
			Snek snek = currentFrame.getSnek();
			Segment currentHead = snekHead();
			Segment nextHead = new Segment(new Point(currentHead.getLocation().x + snek.getDirection().x,
					currentHead.getLocation().y + snek.getDirection().y));
			snek.getSegments().add(0, nextHead);
			boolean b = verifyGoal();
			if (currentFrame.getGoal() instanceof Rock) {
				timePassed += GAME_SPEED;
				Rock r = (Rock) currentFrame.getGoal();
				if (timePassed >= r.getLifeTime()) {
					newGoal(new Apple());
					timePassed = 0;
				}
			}

			if (!b) {
				snek.getSegments().remove(snek.getSegments().size() - 1);

			}
			if (b)
				processGoal();
			if (GameController.GAME_SPEED == 40) {
				HardObstacle hB = (HardObstacle) currentFrame.getMap().getObstacles();
				Point dir = hB.getDirection();
				Segment first = currentFrame.getMap().getObstacles().getSegments().get(0);
				Segment next = new Segment(new Point(first.getLocation().x + dir.x, first.getLocation().y + dir.y));
				if (currentFrame.getMap().getObstacles().getSegments().size() != 34 && grow) {
					if (timePassedObs >= 400) {
						currentFrame.getMap().getObstacles().getSegments().add(0, next);
						System.out.println(currentFrame.getMap().getObstacles().getSegments().size());
						timePassedObs = 0;
					}
					if (timePassedObs != 400)
						timePassedObs += GAME_SPEED;
				}
			}
			if (currentFrame.getMap().getObstacles() instanceof HardObstacle) {
				if (currentFrame.getMap().getObstacles().getSegments().size() == 34 || !grow) {
					grow = false;
					if (timePassedObs >= 400) {
						currentFrame.getMap().getObstacles().getSegments().remove(0);
						if (currentFrame.getMap().getObstacles().getSegments().size() == 20)
							grow = true;

						System.out.println(currentFrame.getMap().getObstacles().getSegments().size());
						timePassedObs = 0;
					}
					if (timePassedObs != 400)
						timePassedObs += GAME_SPEED;
				}
			}
		}

		verifyEndGame();
	}

	private boolean verifyGoal() {

		Segment currentHead = snekHead();
		if (currentHead.getLocation().equals(currentFrame.getGoal().getLocation())) {
			currentFrame.setScore(currentFrame.getGoal().getReward() + currentFrame.getScore());
			System.out.println("NOVA PONTUACAO: " + currentFrame.getScore());

			return true;
		}
		return false;
	}

	public void processGoal() {
		int banana = 0, rock = 0;
		if (currentFrame.getScore() % 30 == 0) {
			banana = 1;
		}
		if (currentFrame.getScore() % 40 == 0)
			rock = 1;

		if (rock == 1) {
			newGoal(new Rock());
		}
		if (banana == 1) {
			newGoal(new Banana());

		}
		if (banana == 0 && rock == 0)
			newGoal(new Apple());

		banana = 0;
		rock = 0;
	}

	private void verifyEndGame() {
		boolean isStillAlive = true;

		if (snekHead().getLocation().x < 0 || snekHead().getLocation().x > currentFrame.getMap().getQtdCellsWidth()
				|| snekHead().getLocation().y < 0
				|| snekHead().getLocation().y > currentFrame.getMap().getQtdCellsHeight() || bodyColision()
				|| obstacleColision())
			isStillAlive = false;
		currentFrame.getSnek().setSnekIsAlive(isStillAlive);
	}

	private Segment snekHead() {
		return currentFrame.getSnek().getSegments().get(0);
	}

	public GameFrame getCurrentFrame() {
		return currentFrame;
	}

	public void newGoal(Goal goal) {
		Point ponto = new Point(random.nextInt(currentFrame.getMap().getQtdCellsWidth()),
				random.nextInt(currentFrame.getMap().getQtdCellsHeight()));
		currentFrame.setGoal(goal);
		currentFrame.getGoal().setLocation(ponto);

	}

	private boolean bodyColision() {
		for (int i = 1; i < currentFrame.getSnek().getSegments().size(); i++) {
			if (snekHead().getLocation().equals(currentFrame.getSnek().getSegments().get(i).getLocation()))
				return true;
		}
		return false;
	}

	private boolean obstacleColision() {
		for (int i = 0; i < currentFrame.getMap().getObstacles().getSegments().size(); i++) {
			if (snekHead().getLocation()
					.equals(currentFrame.getMap().getObstacles().getSegments().get(i).getLocation()))
				return true;

		}
		return false;
	}

	public void initClasses() throws SQLException {
		GameMap map = null;
		ficharioPlayer = new Fichario(new PlayerDao());
		if (GameController.GAME_SPEED == 100) {
			map = new GameMap(30, 24, new Point(5, 12), initEasyObstacle());
		}
		if (GameController.GAME_SPEED == 70) {
			map = new GameMap(30, 24, new Point(5, 12), initNormalObstacle());
		}
		if (GameController.GAME_SPEED == 40) {
			map = new GameMap(30, 24, new Point(5, 12), initHardObstacle(new Point(1, 0)));
		}
		random = new Random();
		ArrayList<Segment> snekSegments = new ArrayList<>();
		snekSegments.add(new Segment(new Point(map.getSpawnPoint().x, map.getSpawnPoint().y)));
		snekSegments.add(new Segment(new Point(map.getSpawnPoint().x - 1, map.getSpawnPoint().y)));
		snekSegments.add(new Segment(new Point(map.getSpawnPoint().x - 2, map.getSpawnPoint().y)));
		Snek snek = new Snek(snekSegments, new Point(1, 0));
		Apple goal = new Apple(new Point(28, 4));

		currentFrame = new GameFrame(snek, goal, map);

	}

	public Obstacle initEasyObstacle() {
		ArrayList<Segment> obstacleSegments = new ArrayList<>();
		Obstacle obstacle = new EasyObstacle();

		int x = 10;
		int y = 10;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x + i, y)));
		}
		x = 4;
		y = 4;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x, y + i)));
		}
		obstacle.setSegments(obstacleSegments);
		return obstacle;
	}

	public Obstacle initNormalObstacle() {
		ArrayList<Segment> obstacleSegments = new ArrayList<>();
		Obstacle obstacle = new NormalObstacle();

		int x = 10;
		int y = 10;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x + i, y)));
		}
		x = 4;
		y = 4;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x, y + i)));
		}
		x = 15;
		y = 10;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x, y + i)));
		}
		obstacle.setSegments(obstacleSegments);
		return obstacle;

	}

	public Obstacle initHardObstacle(Point dir) {
		ArrayList<Segment> obstacleSegments = new ArrayList<>();
		Obstacle obstacle = new HardObstacle(dir);

		int x = 10;
		int y = 10;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x + i, y)));
		}
		x = 4;
		y = 4;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x, y + i)));
		}
		x = 15;
		y = 10;
		for (int i = 0; i < obstacle.getSize(); i++) {
			obstacleSegments.add(new Segment(new Point(x, y + i)));
		}
		obstacle.setSegments(obstacleSegments);
		return obstacle;

	}
}
