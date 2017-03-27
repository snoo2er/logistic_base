package by.hryshchanka.task3.creator;

import java.util.ArrayList;

import by.hryshchanka.task3.entity.LogisticBase;

public class LogisticBaseCreator {
	private static final int TERMINAL_QUANTITY_INDEX = 0;

	public static LogisticBase createLogisticBase(ArrayList<String[]> datalist) {
		LogisticBase base = LogisticBase.getInstance();
		base.initBase(Integer.parseInt(datalist.get(TERMINAL_QUANTITY_INDEX)[0]));
		return base;
	}
}
