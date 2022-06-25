package root.com.aqs;

import sun.misc.Unsafe;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 自定义的Lock，公平、共享锁
 */
public class CustomLock {

	// 当前线程加锁状态和加锁次数
	private volatile int state = 0;

	// 当前持有锁的线程
	private Thread lockHolder;

	// 正在等待获取锁的线程队列，LinkedQueue内部已经实现了AQS所以不用
	private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

	/**
	 * 加锁
	 */
	private void lock() {
		// 加锁成功直接返回
		if (acquire()) {
			return;
		}
		// 不成功：先加入队列进行自旋
		Thread current = Thread.currentThread();
		waiters.add(current);
		for (; ; ) {
			// 如果当前线程是队列的第一个就继续
			if (current == waiters.peek() && acquire()) {
				// 加锁成功的线程移出队列
				waiters.poll();
				return;
			}
			// 阻塞当前线程，此时会释放CPU资源，不会释放占有的锁资源
			LockSupport.park(current);
		}
	}

	/**
	 * 释放锁
	 */
	private void unlock() {
		if (Thread.currentThread() == getLockHolder()) {
			int c = getState();
			if (compareAndSwapState(c, 0)) {
				// 把当前拿到锁的线程置为null
				setLockHolder(null);
				// 唤醒第一个线程
				Optional<Thread> first = Optional.ofNullable(waiters.peek());
				first.ifPresent(LockSupport::unpark);
			}
		} else {
			throw new RuntimeException("LockHolder is not current thread");
		}
	}

	/**
	 * 获取锁
	 *
	 * @return true/false
	 */
	private boolean acquire() {
		Thread current = Thread.currentThread();
		// 获取当前是否被上锁
		int c = getState();
		if (c == 0) {
			// 没有被上锁，且CAS操作成功
			// waiters.size() == 0: 如果有多个线程初次进入这个方法，需要用这个条件判断，如果是第一次以后，可能永远不会为0，就无法获取锁了
			// current == waiters.peek(): 当前线程在队列首位
			if ((waiters.size() == 0 || current == waiters.peek())
							&& compareAndSwapState(0, 1)) {
				setLockHolder(current);
				return true;
			}
		}
		return false;
	}

	/**
	 * CAS修改state的值
	 *
	 * @param except 期望值
	 * @param update 修改值
	 * @return 成功或失败
	 */
	private final boolean compareAndSwapState(int except, int update) {
		return unsafe.compareAndSwapInt(this, stateOffset, except, update);
	}

	private static final Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

	// state字段在CustomLock.class内存结构里面的偏移量
	private static final long stateOffset;

	static {
		try {
			// 获取偏移量
			stateOffset = unsafe.objectFieldOffset(CustomLock.class.getDeclaredField("state"));
		} catch (Exception e) {
			throw new Error();
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Thread getLockHolder() {
		return lockHolder;
	}

	public void setLockHolder(Thread lockHolder) {
		this.lockHolder = lockHolder;
	}

	public ConcurrentLinkedQueue<Thread> getWaiters() {
		return waiters;
	}

	public void setWaiters(ConcurrentLinkedQueue<Thread> waiters) {
		this.waiters = waiters;
	}
}
