package by.hryshchanka.task3.entity;

import java.util.concurrent.atomic.AtomicBoolean;

public class Terminal {
	private long id;
	private AtomicBoolean inUse = new AtomicBoolean(false);

	public Terminal(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AtomicBoolean getInUse() {
		return inUse;
	}

	public void setInUse(AtomicBoolean inUse) {
		this.inUse = inUse;
	}

	@Override
	public String toString() {
		StringBuffer strb = new StringBuffer("terminal#" + id);
		if (inUse.get()) {
			strb.append(" is in use");
		} else {
			strb.append(" is free");
		}
		return strb.toString();
	}
}
