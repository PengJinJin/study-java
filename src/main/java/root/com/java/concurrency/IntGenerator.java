package root.com.java.concurrency;

public abstract class IntGenerator {

	private volatile boolean canceled = false;

	public abstract int next();

	public boolean isCanceled() {
		return canceled;
	}

	public void cancel() {
		canceled = true;
	}
}
