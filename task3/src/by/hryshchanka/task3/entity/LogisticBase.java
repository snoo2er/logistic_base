package by.hryshchanka.task3.entity;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import by.hryshchanka.task3.action.TerminalIdAssigner;

public class LogisticBase {
	private CopyOnWriteArrayList<Terminal> terminals = new CopyOnWriteArrayList<>();
	private AtomicInteger freeTerminals;
	private static LogisticBase instance;
	private static ReentrantLock lock = new ReentrantLock();
	private static AtomicBoolean exist = new AtomicBoolean(false);

	private LogisticBase() {
	}

	public static LogisticBase getInstance() {
		if (!exist.get()) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new LogisticBase();
					exist.set(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public void initBase(int terminalQuantity) {
		for (int i = 0; i < terminalQuantity; i++) {
			terminals.add(new Terminal(TerminalIdAssigner.assignId()));
		}
		freeTerminals = new AtomicInteger(terminals.size());
	}

	public Terminal findTerminal(int index) {
		return terminals.get(index);
	}

	public AtomicInteger getFreeTerminals() {
		return freeTerminals;
	}

	public void setFreeTerminals(AtomicInteger freeTerminals) {
		this.freeTerminals = freeTerminals;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(
				"\nLogistic Base\nNumber of Terminals: " + terminals.size() + "\nFree terminals: " + freeTerminals);
		for (Terminal terminal : terminals) {
			sb.append("\nterminal " + terminal.getId());
			if (terminal.getInUse().get()) {
				sb.append(" is busy");
			} else {
				sb.append(" is free");
			}
		}
		return sb.toString() + "\n";
	}
}
