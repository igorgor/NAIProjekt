package Projekt;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Point {
	int x;
	int y;
	Color color;
	General general;
	double distance;

	public Point(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.distance = 1300;
	}

	public static List<Point> generatePoints(int amount, int size) {
		Random rand = new Random();
		List<Point> pointList = new ArrayList<Point>();
		for (int i = 0; i < amount; i++) {
			Point tmp = new Point(rand.nextInt(size), rand.nextInt(size), Color.BLACK);
			pointList.add(tmp);
		}
		return pointList;
	}

	
	public static double euclideanDistance(Point p1, Point p2) {
		double tmp1 = p1.getX() - p2.getX();
		double tmp2 = p1.getY() - p2.getY();
		tmp1 *= tmp1;
		tmp2 *= tmp2;
		return Math.sqrt(tmp1 + tmp2);
	}

	public static double manhattanDistance(Point p1, Point p2) {
		double tmp1 = p1.getX() - p2.getX();
		double tmp2 = p1.getY() - p2.getY();
		tmp1 = Math.abs(tmp1);
		tmp2 = Math.abs(tmp2);
		return tmp1 + tmp2;
	}

	public static double manhattanAndEuclideanDistance(Point p1, Point p2) {
		return euclideanDistance(p1, p2) + manhattanDistance(p1, p2);
	}

	public static double chebyshevDistance(Point p1, Point p2) {
		return Math.max(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
	}

	public static double myDistance(Point p1, Point p2) {
		Random rand = new Random();
		int tmp = rand.nextInt(4);
		double tmp2 = 0;
		if (tmp == 0)
			tmp2 = euclideanDistance(p1, p2);
		if (tmp == 1)
			tmp2 = manhattanDistance(p1, p2);
		if (tmp == 2)
			tmp2 = manhattanAndEuclideanDistance(p1, p2);
		if (tmp == 3)
			tmp2 = chebyshevDistance(p1, p2);
		return tmp2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general, double distance) {
		this.general = general;
		setColor(general.getColor());
		setDistance(distance);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
