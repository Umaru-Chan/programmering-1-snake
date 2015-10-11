/**
 * @(#)Square.java
 *
 *
 * @author
 * @version 1.00 2008/8/16
 */

public class SquareMod {
	private int x;
	private int y;
	private int side;

	public SquareMod(int x, int y, int side) {
		this.x = x;
		this.y = y;
		this.side = side;
	}

	public void draw(SimpleWindowMod w) {
		w.moveTo(x, y);
		w.lineTo(x, y + side);
		w.lineTo(x + side, y + side);
		w.lineTo(x + side, y);
		w.lineTo(x, y);
	}

	public void move(int dx, int dy) {
		x = x + dx;
		y = y + dy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSide() {
		return side;
	}

	public int getArea() {
		return side * side;
	}
}
