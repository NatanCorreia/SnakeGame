package helpers;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import model.Apple;
import model.Banana;
import model.GameFrame;
import model.Goal;
import model.Rock;
import model.Segment;
import view.MainFrame;

public class LoadSave {
	private Goal g;

	public void processAndWrite(String string, GameFrame frame) {

		File txtFile = new File("res/" + string + ".txt");
		try {
			PrintWriter pw = new PrintWriter(txtFile);
			pw.println(frame.getGoal().getLocation().x);
			pw.println(frame.getGoal().getLocation().y);
			pw.println(checkGoal(frame));
			pw.println(frame.getScore());
			pw.println(frame.getSnek().getDirection().x);
			pw.println(frame.getSnek().getDirection().y);
			pw.println(frame.getDifficulty());
			pw.println(MainFrame.PLAYER_ID);
			for (int i = 0; i < frame.getSnek().getSegments().size(); i++) {
				pw.println(frame.getSnek().getSegments().get(i).getLocation().x);
				pw.println(frame.getSnek().getSegments().get(i).getLocation().y);
			}
			pw.println("/");
			for (int i = 0; i < frame.getMap().getObstacles().getSegments().size(); i++) {
				pw.println(frame.getMap().getObstacles().getSegments().get(i).getLocation().x);
				pw.println(frame.getMap().getObstacles().getSegments().get(i).getLocation().y);
			}

			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public GameFrame processAndLoad(String fileName, GameFrame frame) {
		File file = new File("res/" + fileName);
		String x = "nada", y;
		ArrayList<Segment> sSeg = new ArrayList<Segment>();
		ArrayList<Segment> mSeg = new ArrayList<Segment>();
		try {
			Scanner sc = new Scanner(file);

			frame.setGoal(checkAndSetGoal(sc.nextLine(), sc.nextLine(), sc.nextLine()));
			frame.setScore(Integer.parseInt(sc.nextLine()));
			frame.getSnek().setDirection(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
			frame.setDifficulty(Integer.parseInt(sc.nextLine()));
			frame.setPlayerId(Integer.parseInt(sc.nextLine()));
			while (sc.hasNextLine()) {
				x = sc.nextLine();
				if (x.equals("/"))
					break;
				y = sc.nextLine();
				sSeg.add(new Segment(new Point(Integer.parseInt(x), Integer.parseInt(y))));

			}
			frame.getSnek().setSegments(sSeg);
			while (sc.hasNextLine()) {
				mSeg.add(new Segment(new Point(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()))));
			}
			frame.getMap().getObstacles().setSegments(mSeg);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return frame;
	}

	private Goal checkAndSetGoal(String x, String y, String tipo) {
		if (tipo.equals("0"))
			g = new Apple(Integer.parseInt(x), Integer.parseInt(y));
		if (tipo.equals("1"))
			g = new Banana(Integer.parseInt(x), Integer.parseInt(y));
		if (tipo.equals("2"))
			g = new Rock(Integer.parseInt(x), Integer.parseInt(y));
		return g;
	}

	private int checkGoal(GameFrame frame) {
		int i = 0;
		if (frame.getGoal() instanceof Apple)
			i = 0;
		if (frame.getGoal() instanceof Banana)
			i = 1;
		if (frame.getGoal() instanceof Rock)
			i = 2;

		return i;
	}
}
