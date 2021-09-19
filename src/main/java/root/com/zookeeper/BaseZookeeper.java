package root.com.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BaseZookeeper implements Watcher {

	private ZooKeeper zooKeeper;
	private static final int SESSION_TIME_OUT = 2000;
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public void process(WatchedEvent watchedEvent) {
		if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
			System.out.println("Watch received event");
			countDownLatch.countDown();
		}
	}

	/**
	 * ����Zookeeper
	 *
	 * @param host
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void connectZookeeper(String host) throws IOException, InterruptedException {
		zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
		countDownLatch.await();
		System.out.println("Zookeeper connected success");
	}

	/**
	 * �����ڵ�
	 *
	 * @param path
	 * @param data
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String createNode(String path, String data) throws KeeperException, InterruptedException {
		return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
	}

	/**
	 * ��ȡ�ӽڵ㼯
	 *
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public List<String> getChildren(String path) throws KeeperException, InterruptedException {
		return zooKeeper.getChildren(path, false);
	}

	/**
	 * ��ȡ�ڵ��ϵ�����
	 *
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String getData(String path) throws KeeperException, InterruptedException {
		byte[] data = zooKeeper.getData(path, false, null);
		return data == null ? "" : new String(data);
	}

	/**
	 * ���ڵ���������
	 *
	 * @param path
	 * @param data
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public Stat setData(String path, String data) throws KeeperException, InterruptedException {
		return zooKeeper.setData(path, data.getBytes(), -1);
	}

	/**
	 * ɾ���ڵ�
	 *
	 * @param path
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void deleteNode(String path) throws KeeperException, InterruptedException {
		zooKeeper.delete(path, -1);
	}

	/**
	 * ��ȡ�ڵ㴴��ʱ��
	 *
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public String getCTime(String path) throws KeeperException, InterruptedException {
		Stat stat = zooKeeper.exists(path, false);
		return String.valueOf(stat.getCtime());
	}

	/**
	 * ��ȡ�ӽڵ�����
	 *
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public Integer getChildrenNum(String path) throws KeeperException, InterruptedException {
		return zooKeeper.getChildren(path, false).size();
	}

	/**
	 * �ر�����
	 *
	 * @throws InterruptedException
	 */
	public void closeConnection() throws InterruptedException {
		if (zooKeeper != null) {
			zooKeeper.close();
		}
	}

}
