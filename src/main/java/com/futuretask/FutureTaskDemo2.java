package com.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * 这个例子的场景<br/>
 * 假设有一个带key的连接池，当key存在时，即直接返回key对应的对象；当key不存在时，则创建连接<br/>
 * 确保高并发环境下的线程安全，也确保了connection只创建一次
 */
public class FutureTaskDemo2 {

	private class Connection {
	}

	//创建Connection
	private Connection createConnection() {
		return null;
	}

	private ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<>();

	public Connection getConnection(String key) throws Exception {
		FutureTask<Connection> connectionFutureTask = connectionPool.get(key);
		if (connectionFutureTask == null) {
			Callable<Connection> callable = this::createConnection;

			FutureTask<Connection> newTask = new FutureTask<>(callable);
			connectionFutureTask = connectionPool.putIfAbsent(key, newTask);
			// 如果返回null表示不存在该key,需要调用run方法执行
			// 如果已经存在，那么不会覆盖已有的值，直接返回已经存在的值ha's
			if (connectionFutureTask == null) {
				connectionFutureTask = newTask;
				connectionFutureTask.run();
			}
			return null;
		}
		return connectionFutureTask.get();
	}

}
