package controller;

import model.Apple;
import model.Banana;
import model.GameFrame;
import model.GameMap;
import model.Goal;
import model.Obstacle;
import model.Rock;
import model.Segment;
import model.Snek;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

	private GameFrame currentFrame = null;
	public static final int GAME_SPEED = 70; // millis/frame
	private Random random;
	private int timePassed =0;
	private ArrayList<Point> pontos;

	public void init() {
		ArrayList<Segment> obstacleSegments = new ArrayList<>();
		
		random = new Random();
		int x = random.nextInt(28);
		int y = random.nextInt(22);
		obstacleSegments.add(new Segment(new Point(20,20)));
	
		Obstacle obstacle = new Obstacle(obstacleSegments);
		
		
		
		GameMap map = new GameMap(30,24, new Point(5, 12), obstacle);
		
		ArrayList<Segment> snekSegments = new ArrayList<>();
		snekSegments.add(new Segment(new Point(map.getSpawnPoint().x, map.getSpawnPoint().y)));
		snekSegments.add(new Segment(new Point(map.getSpawnPoint().x-1, map.getSpawnPoint().y)));
		snekSegments.add(new Segment(new Point(map.getSpawnPoint().x-2, map.getSpawnPoint().y)));
		Snek snek = new Snek(snekSegments, new Point(1,0));
		
		
		Apple goal = new Apple(new Point(28,4));
		
		currentFrame = new GameFrame(snek, goal, map);
		
		
	}
	
	public void processKeyEvent(KeyEvent evt) {
		int code = evt.getKeyCode();
		
		if(code == KeyEvent.VK_UP) {
			if(currentFrame.getSnek().getVelocity().y!=1)
			currentFrame.getSnek().setVelocity(new Point(0, -1));
        }
        else if(code == KeyEvent.VK_RIGHT) {
        	if(currentFrame.getSnek().getVelocity().x!=-1)
        	currentFrame.getSnek().setVelocity(new Point(1, 0));
        }
        else if(code == KeyEvent.VK_LEFT) {
        	if(currentFrame.getSnek().getVelocity().x!=1)
        	currentFrame.getSnek().setVelocity(new Point(-1, 0));}
        else if(code == KeyEvent.VK_DOWN) {
        	if(currentFrame.getSnek().getVelocity().y!=-1)
        	currentFrame.getSnek().setVelocity(new Point(0, 1));}
        else if(code == KeyEvent.VK_SPACE && currentFrame.isPausou()) {
        	
        	currentFrame.setPausou(false);
        }
        else if(code == KeyEvent.VK_SPACE) {
        	
        	currentFrame.setPausou(true);
        }
	}
	
	public void generateNextFrame() {
		
		if(currentFrame == null) {
			throw new IllegalStateException("current frame is null. Did you call 'init'?");
		}
		
			
		if(currentFrame.getSnek().isSnekIsAlive() && !currentFrame.isPausou()) {
			Snek snek = currentFrame.getSnek();
			Segment currentHead = snekHead();
			Segment nextHead = new Segment(new Point(currentHead.getLocation().x + snek.getVelocity().x, currentHead.getLocation().y + snek.getVelocity().y));
			snek.getSegments().add(0, nextHead);
			boolean b = verifyGoal();
			if(currentFrame.getGoal() instanceof Rock) { 
				timePassed +=GAME_SPEED;
				Rock r = (Rock)currentFrame.getGoal();
				if(timePassed>=r.getLifeTime()) {
					newGoal(new Apple());
					timePassed=0;}
				}
				
			if(!b) {
				snek.getSegments().remove(snek.getSegments().size()-1);
				
			}
			if(b) 
				processGoal();
				
			verifyEndGame();
		}
	}
	
	private boolean verifyGoal() {
		
		Segment currentHead = snekHead();
		if(currentHead.getLocation().equals(currentFrame.getGoal().getLocation())) {
			currentFrame.setScore(currentFrame.getGoal().getReward()+ currentFrame.getScore());
			System.out.println("NOVA PONTUACAO: " + currentFrame.getScore());
		
			return true;
		}
		return false;
	}
	public void processGoal() {
		int banana =0, rock =0;
		if(currentFrame.getScore()%30==0) {
			banana=1;
		}
		if(currentFrame.getScore()%40==0)
			rock=1;
		
		if(rock==1) {
			newGoal(new Rock());
		}
		if(banana==1) {
			newGoal(new Banana());
			
			}
		if(banana==0 && rock==0) 
		newGoal(new Apple());
		
		banana=0;
		rock=0;
	}
	
	private void verifyEndGame() {
		boolean isStillAlive = true;
		if(snekHead().getLocation().x<0 || snekHead().getLocation().x>currentFrame.getMap().getQtdCellsWidth() || snekHead().getLocation().y<0 
				|| snekHead().getLocation().y>currentFrame.getMap().getQtdCellsHeight() || bodyColision() || obstacleColision())
			isStillAlive=false;
		currentFrame.getSnek().setSnekIsAlive(isStillAlive); 
	}
	
	private Segment snekHead() {
		return currentFrame.getSnek().getSegments().get(0);
	}
	
	public GameFrame getCurrentFrame() {
		return currentFrame;
	}
	public void newGoal(Goal goal) {
		Point ponto = new Point(random.nextInt(currentFrame.getMap().getQtdCellsWidth()),random.nextInt(currentFrame.getMap().getQtdCellsHeight()));
		currentFrame.setGoal(goal);
		currentFrame.getGoal().setLocation(ponto);
		
	}
	public boolean bodyColision() {
		for(int i = 1; i<currentFrame.getSnek().getSegments().size();i++) {
			if(snekHead().getLocation().equals(currentFrame.getSnek().getSegments().get(i).getLocation()))
				return true;
		}
		return false;
	}
	public boolean obstacleColision() {
		
			if(snekHead().getLocation().equals(new Point(20,20))) {
				System.out.println("True");
				return true;}
			else
			{ System.out.println("False");
		return false;}
	}
}
