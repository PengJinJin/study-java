package root.com.kkx.day01.homework;

public abstract class E06_Graphics {

	protected double calcArea() {
		return 0D;
	}

	protected boolean isInner(Point point) {
		return false;
	}

}

class Circle extends E06_Graphics {

	private int r;
	/**
	 * 圆心，也是一个点
	 */
	private Point center;

	@Override
	protected double calcArea() {
		return 3.14 * r * r;
	}

	@Override
	protected boolean isInner(Point point) {
		int a = 0;//圆心坐标x与点坐标x之间的距离
		int b = 0;//圆心坐标y与点坐标Y之间的距离
		//如果圆心的坐标，与点的坐标同号。则做减法，再取绝对值。算出距离
		//如果圆心坐标，与点异号。先取绝对值。再做加法。
		if (center.getX() * point.getX() >= 0 || center.getY() * point.getY() >= 0) {
			a = Math.abs(center.getX() - point.getX());
			b = Math.abs(center.getY() - point.getY());
		} else if (center.getX() * point.getX() < 0 || center.getY() * point.getY() < 0) {
			a = Math.abs(center.getX()) + Math.abs(point.getX());
			b = Math.abs(center.getY()) + Math.abs(point.getY());
		} else {
			System.out.println("点输入有误");
		}
		int d = a * a + b * b;
		if (d <= r * r) {
			System.out.println("点" + point.toString() + "在圆内");
			return true;
		} else {
			System.out.println("不在圆内");
			return false;
		}

	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
}

class Rectangle extends E06_Graphics {
	private int w;
	private int h;

	@Override
	protected double calcArea() {
		return w * h;
	}

	@Override
	protected boolean isInner(Point point) {
		// TODO
		return super.isInner(point);
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
}

class Point {
	private int x;
	private int y;

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

	@Override
	public String toString() {
		return "(x=" + x + ",y=" + y + ")";
	}
}

class Test {

	public static void main(String[] args) {
		System.out.println("计算面积=======");
		E06_Graphics graphics = new Circle();
		Circle circle = (Circle) graphics;
		circle.setR(5);
		double area = circle.calcArea();
		System.out.println("圆的面积" + area);
		E06_Graphics rectangle = new Rectangle();
		Rectangle rectangle1 = (Rectangle) rectangle;
		rectangle1.setH(3);
		rectangle1.setW(4);
		double area1 = rectangle1.calcArea();
		System.out.println("长方形的面积" + area1);
		System.out.println("判断点是否在圆内=======");
		Point x1 = new Point();
		Point x2 = new Point();
		x1.setX(-1);
		x1.setY(-2);
		circle.setCenter(x1);
		x2.setX(100);
		x2.setY(100);
		System.out.println(circle.isInner(x2));

	}
}
