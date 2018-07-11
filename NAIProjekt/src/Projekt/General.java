package Projekt;
//elo
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class General extends Point {
	private List<Point> pointList;
	private static Color[] colorTab = { Color.BLACK, Color.YELLOW, Color.RED, Color.GRAY, Color.MAGENTA,
			Color.DARK_GRAY, Color.CYAN, Color.LIGHT_GRAY, Color.ORANGE, Color.WHITE, Color.GREEN, Color.PINK,
			Color.BLUE, };

	public General(int x, int y, Color color) {
		super(x, y, color);
		pointList = new ArrayList<Point>();
	}

	public static List<General> generateGenerals(int amount, int size) {
		Random rand = new Random();
		List<General> generalList = new ArrayList<General>();
		for (int i = 0; i < amount; i++) {
			if (i < 13) {
				General tmp = new General(rand.nextInt(size + 1), rand.nextInt(size + 1), colorTab[i]);
				generalList.add(tmp);
			} else {
				General tmp = new General(rand.nextInt(size + 1), rand.nextInt(size + 1),
						colorTab[rand.nextInt(colorTab.length)]);
				generalList.add(tmp);
			}
		}
		return generalList;
	}

	public List<Point> getList() {
		return pointList;
	}

	public void addPoint(Point p) {
		pointList.add(p);
	}

	public void resetList() {
		pointList.clear();
	}
}
