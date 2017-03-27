package by.hryshchanka.task3.entity;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.hryshchanka.task3.action.VanAction;

public class Van extends Thread {
	private static final Logger LOGGER = LogManager.getLogger(Van.class);
	private long id;
	private boolean loaded;
	private boolean perishableGoods;

	public Van(long id, boolean loaded, boolean perishableGoods) {
		super();
		this.id = id;
		this.loaded = loaded;
		this.perishableGoods = perishableGoods;
	}

	public Van(long id, boolean loaded) {
		super();
		this.id = id;
		this.loaded = loaded;
	}

	@Override
	public long getId() {
		return id;
	}

	public boolean isPerishableGoods() {
		return perishableGoods;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public void setPerishableGoods(boolean perishableGoods) {
		this.perishableGoods = perishableGoods;
	}

	@Override
	public void run() {
		LOGGER.log(Level.INFO, toString() + " starts");
		try {
			TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
			LOGGER.log(Level.INFO, toString() + " came to base");
			VanAction.takeSpotInQueue(this);
			VanAction.waitFirstPlace(this);
			int freeTerminalIndex = VanAction.findFreeTerminal(this);
			VanAction.useTerminal(freeTerminalIndex, this);
			VanAction.leaveBase(freeTerminalIndex, this);
		} catch (InterruptedException e) {
			LOGGER.log(Level.ERROR, e + "run error");
		}
	}

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder("van#" + id);
		if (loaded && perishableGoods) {
			strb.append(" loaded with perishable goods");
		} else if (loaded && !perishableGoods) {
			strb.append(" loaded with not perishable goods");
		} else {
			strb.insert(0, "empty ");
		}
		return strb.toString();
	}
}