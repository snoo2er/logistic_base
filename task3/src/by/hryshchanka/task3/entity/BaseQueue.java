package by.hryshchanka.task3.entity;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class BaseQueue {
	private static BaseQueue instance;
	private static ReentrantLock lock = new ReentrantLock();
	private static AtomicBoolean exist = new AtomicBoolean(false);

	private CopyOnWriteArrayList<Van> queue = new CopyOnWriteArrayList<>();

	private BaseQueue() {
	}

	public static BaseQueue getInstance() {
		if (!exist.get()) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new BaseQueue();
					exist.set(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public CopyOnWriteArrayList<Van> getQueue() {
		return queue;
	}
}
