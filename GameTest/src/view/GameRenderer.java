package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import controller.GameStates;
import model.GameFrame;
import model.Segment;

public class GameRenderer {
	
	private Dimension panelSize;
	
	public GameRenderer(Dimension panelSize) {
		this.panelSize = panelSize;
	}

	public void renderFrame(GameFrame frame, Graphics g) {
		switch(GameStates.gameState) {
		case MENU:
			break;
		case PLAYING:
			drawGame(frame,g);
			break;
		case USERDATA:
			break;
		}
	}
	public void drawEndGame(GameFrame frame, Graphics g) {
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		g.setColor(Color.red);
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		g.drawString("Game Over", (panelSize.width - metrics.stringWidth("Game Over"))/2, panelSize.height/2);
		
	}
	
	public void drawGame(GameFrame frame, Graphics g) {
		int cellSize = Math.min(panelSize.width/frame.getMap().getQtdCellsWidth(),panelSize.height/frame.getMap().getQtdCellsHeight());
		//desenha os limites to mapa
		g.setColor(Color.blue);
		
		g.drawRect(0,0, cellSize * frame.getMap().getQtdCellsWidth(), cellSize * frame.getMap().getQtdCellsHeight());
		
		// desenha o objetivo
		g.setColor(frame.getGoal().getColor());
		g.fillOval(cellSize * frame.getGoal().getLocation().x, cellSize * frame.getGoal().getLocation().y, cellSize, cellSize);
		
		//desenha a kroba
		int snekLenght = frame.getSnek().getSegments().size();
		g.setColor(new Color(45,180,0));
		for(int i = 0; i < snekLenght; i++) {
			Segment currentSegment = frame.getSnek().getSegments().get(i);
			g.fillRect(cellSize * currentSegment.getLocation().x, cellSize * currentSegment.getLocation().y, cellSize, cellSize);
			g.setColor(Color.green);
		}
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		g.drawString("SCORE: " + frame.getScore(), (panelSize.width - metrics.stringWidth("SCORE: " + frame.getScore()))/2, panelSize.height-g.getFont().getSize());
		for(int i = 0; i<frame.getMap().getObstacles().get(0).getSegments().size();i++) {
		g.fill3DRect((frame.getMap().getObstacles().get(0).getSegments().get(i).getLocation().x),(frame.getMap().getObstacles().get(0).getSegments().get(i).getLocation().y),cellSize,cellSize,true);}
		// desenha obstaculos, o score atual...
	}
}
