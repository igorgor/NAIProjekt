package Projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PointWindow extends JPanel {
	private static final long serialVersionUID = 1L;
	private int distance;
	private JFrame frame;
	private List<Point> pointList;
	private List<General> generalList;
	private List<General> oldGeneralList;
	private boolean isFinished;

	public static void main(String[] args) {
		int points = Integer.parseInt(JOptionPane.showInputDialog("Enter number of points: "));
		int generals = Integer.parseInt(JOptionPane.showInputDialog("Enter numbers of generals: "));
		int distance = Integer.parseInt(
				JOptionPane.showInputDialog("1 - Euclidean distance\n2 - Manhattan distance\n3 - Chebyshev distance"));
		/*int distance = Integer.parseInt(
				JOptionPane.showInputDialog("1 - Euclidean distance\n2 - Manhattan distance\n3 - Chebyshev distance\n4 - Akritean distance"));*/
		PointWindow test = new PointWindow(points, generals, distance);
		test.start();
	}

	public PointWindow(int points, int generals, int distance) {
		this.distance = distance;
		isFinished = false;
		pointList = Point.generatePoints(points, 1200);
		generalList = General.generateGenerals(generals, 1200);
		oldGeneralList = new ArrayList<General>();
		for (General g : generalList)
			oldGeneralList.add(new General(g.getX(), g.getY(), g.getColor()));
		setPreferredSize(new Dimension(1200, 1200));
		frame = new JFrame("Centroids");
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		// setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		frame.pack();
		frame.setVisible(true);
		//frame.setResizable(false);
	}

	public void start() {
		int iteration = 0;
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			execute();
			frame.repaint();
			int tmp = 0;
			for (int i = 0; i < generalList.size(); i++) {
				if (generalList.get(i).getX() == oldGeneralList.get(i).getX()
						&& generalList.get(i).getY() == oldGeneralList.get(i).getY()) {
					tmp++;
				}
			}
			if (tmp == generalList.size())
				isFinished = true;
			oldGeneralList.clear();
			for (General g : generalList)
				oldGeneralList.add(new General(g.getX(), g.getY(), g.getColor()));
			iteration++;
		} while (!isFinished);
		JOptionPane.showMessageDialog(null, "Done! Iteration no. " + iteration, "", JOptionPane.INFORMATION_MESSAGE);
	}

	public void execute() {
		if (distance == 1) {
			for (Point p : pointList) {
				General tmpGeneral = null;
				for (General g : generalList) {
					double distance = Point.euclideanDistance(p, g);
					if (p.distance > distance) {
						p.setGeneral(g, distance);
						tmpGeneral = g;
					}
				}
				tmpGeneral.addPoint(p);
				p.setDistance(13000);
			}
		}
		if (distance == 2) {
			for (Point p : pointList) {
				General tmpGeneral = null;
				for (General g : generalList) {
					double distance = Point.manhattanDistance(p, g);
					if (p.distance > distance) {
						p.setGeneral(g, distance);
						tmpGeneral = g;
					}
				}
				tmpGeneral.addPoint(p);
				p.setDistance(13000);
			}
		}

		if (distance == 3) {
			for (Point p : pointList) {
				General tmpGeneral = null;
				for (General g : generalList) {
					double distance = Point.chebyshevDistance(p, g);
					if (p.distance > distance) {
						p.setGeneral(g, distance);
						tmpGeneral = g;
					}
				}
				tmpGeneral.addPoint(p);
				p.setDistance(1300000);
			}
		}

		if (distance == 4) {
			for (Point p : pointList) {
				General tmpGeneral = null;
				for (General g : generalList) {
					double distance = Point.manhattanAndEuclideanDistance(p, g);
					if (p.distance > distance) {
						p.setGeneral(g, distance);
						tmpGeneral = g;
					}
				}
				tmpGeneral.addPoint(p);
				p.setDistance(1300000);
			}
		}

		if (distance == 5) {
			for (Point p : pointList) {
				General tmpGeneral = null;
				for (General g : generalList) {
					double distance = Point.myDistance(p, g);
					if (p.distance > distance) {
						p.setGeneral(g, distance);
						tmpGeneral = g;
					}
				}
				tmpGeneral.addPoint(p);
				p.setDistance(1300000);
			}
		}

		for (General g : generalList) {
			double tmpX = 0.0;
			double tmpY = 0.0;
			for (Point p : g.getList()) {
				tmpX += p.getX();
				tmpY += p.getY();
			}
			tmpX /= g.getList().size();
			tmpY /= g.getList().size();
			g.setX((int) tmpX);
			g.setY((int) tmpY);
			g.resetList();
		}
	}

	public void paintComponent(Graphics g) {
		for (Point p : pointList) {
			g.setColor(p.getColor());
			g.fillRect(p.getX(), p.getY(), 1, 1);
			;
		}
		for (General g1 : generalList) {
			g.setColor(Color.BLACK);
			g.fillOval(g1.getX() - 1, g1.getY() - 1, 12, 12);
			g.setColor(g1.getColor());
			g.fillOval(g1.getX(), g1.getY(), 10, 10);
		}
	}
}
