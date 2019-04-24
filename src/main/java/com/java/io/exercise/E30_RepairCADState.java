package com.java.io.exercise;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.java.util.Print.print;

abstract class Shape implements Serializable {
	public static final int RED = 1, BLUE = 2, GREEN = 3;
	private int xPos, yPos, dimension;
	private static Random rand = new Random(47);
	private static int counter = 0;

	public abstract void setColor(int newColor);

	public abstract int getColor();

	public Shape(int xVal, int yVal, int dim) {
		xPos = xVal;
		yPos = yVal;
		dimension = dim;
	}

	public String toString() {
		return getClass() +
				"color[" + getColor() + "] xPos[" + xPos +
				"] yPos[" + yPos + "] dim[" + dimension + "]\n";
	}

	public static Shape randomFactory() {
		int xVal = rand.nextInt(100);
		int yVal = rand.nextInt(100);
		int dim = rand.nextInt(100);
		switch (counter++ % 3) {
			default:
			case 0:
				return new Circle(xVal, yVal, dim);
			case 1:
				return new Square(xVal, yVal, dim);
			case 2:
				return new Line(xVal, yVal, dim);
		}
	}
}

class Circle extends Shape {
	private static int color;

	public static void serializeStaticState(ObjectOutputStream out) throws IOException {
		out.writeInt(color);
	}

	public static void deserializeStaticState(ObjectInputStream in) throws IOException {
		color = in.readInt();
	}

	public Circle(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
	}

	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}
}

class Square extends Shape {

	private static int color;

	public static void serializeStaticState(ObjectOutputStream os)
			throws IOException {
		os.writeInt(color);
	}

	public static void deserializeStaticState(ObjectInputStream os)
			throws IOException {
		color = os.readInt();
	}

	public Square(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
	}

	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}
}

class Line extends Shape {

	private static int color;

	public static void serializeStaticState(ObjectOutputStream os)
			throws IOException {
		os.writeInt(color);
	}

	public static void deserializeStaticState(ObjectInputStream os)
			throws IOException {
		color = os.readInt();
	}

	public Line(int xVal, int yVal, int dim) {
		super(xVal, yVal, dim);
	}

	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}
}


public class E30_RepairCADState {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		List<Shape> shapes = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			shapes.add(Shape.randomFactory());
		}
		for (int i = 0; i < 10; i++) {
			shapes.get(i).setColor(Shape.GREEN);
		}
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CADState.out"));
		Circle.serializeStaticState(out);
		Square.serializeStaticState(out);
		Line.serializeStaticState(out);
		out.writeObject(shapes);

		// Display the shapes:
		print(shapes);
		// Now read the file back in:
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
		// Read in the same order they were written:
		Circle.deserializeStaticState(in);
		Square.deserializeStaticState(in);
		Line.deserializeStaticState(in);
		shapes = (List<Shape>) in.readObject();
		print(shapes);
	}

}
