package by.hryshchanka.task3.action;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.hryshchanka.task3.entity.BaseQueue;
import by.hryshchanka.task3.entity.LogisticBase;
import by.hryshchanka.task3.entity.Van;

public class VanAction {
	private static final Logger LOGGER = LogManager.getLogger(VanAction.class);

	public static void takeSpotInQueue(Van van) {
		if (!van.isPerishableGoods() || BaseQueue.getInstance().getQueue().isEmpty()) {
			BaseQueue.getInstance().getQueue().add(BaseQueue.getInstance().getQueue().size(), van);
		} else {
			int i = 0;
			while (BaseQueue.getInstance().getQueue().get(i).isPerishableGoods()) {
				i++;
			}
			BaseQueue.getInstance().getQueue().add(i, van);
		}
		LOGGER.log(Level.INFO,
				van.toString() + " took spot#" + (BaseQueue.getInstance().getQueue().indexOf(van) + 1) + " in queue");
	}

	public static void waitFirstPlace(Van van) {
		while (!BaseQueue.getInstance().getQueue().get(0).equals(van)
				|| LogisticBase.getInstance().getFreeTerminals().get() < 1) {
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				LOGGER.log(Level.ERROR, e.toString());
			}
		}
		LOGGER.log(Level.INFO, van.toString() + " first in queue");
	}

	public static int findFreeTerminal(Van van) {
		int i = 0;
		while (LogisticBase.getInstance().findTerminal(i).getInUse().get()) {
			i++;
		}
		LOGGER.log(Level.INFO, van.toString() + " drives to terminal#" + i);
		return i;
	}

	public static void useTerminal(int freeTerminalIndex, Van van) {
		try {
			LogisticBase.getInstance().findTerminal(freeTerminalIndex).setInUse(new AtomicBoolean(true));
			LOGGER.log(Level.INFO, LogisticBase.getInstance().findTerminal(freeTerminalIndex));
			LogisticBase.getInstance().setFreeTerminals(new AtomicInteger(LogisticBase.getInstance().getFreeTerminals().get() - 1));
			BaseQueue.getInstance().getQueue().remove(0);
			LOGGER.log(Level.INFO, van.toString() + " using terminal#"+ LogisticBase.getInstance().findTerminal(freeTerminalIndex).getId());
			TimeUnit.SECONDS.sleep((long) (Math.random() * 5 + 5));
			if (van.isLoaded()) {
				van.setLoaded(false);
			} else {
				van.setLoaded(true);
				van.setPerishableGoods(Math.random() < 0.5);
			}
		} catch (InterruptedException e) {
			LOGGER.log(Level.ERROR, e);
		}
	}

	public static void leaveBase(int termIndex, Van van) {
		LOGGER.log(Level.INFO, van.toString() + " leave base");
		LogisticBase.getInstance().findTerminal(termIndex).setInUse(new AtomicBoolean(false));
		LOGGER.log(Level.INFO, LogisticBase.getInstance().findTerminal(termIndex));
		LogisticBase.getInstance().setFreeTerminals(new AtomicInteger(LogisticBase.getInstance().getFreeTerminals().get() + 1));
	}

}
