package root.com.java.containers;

public class GroundHog {

	protected int number;

	public GroundHog(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "GroundHog #" + number;
	}
}
