package by.hryshchanka.task3.action;

public class VanIdAssigner {
	private static long id;

	public static long assignId() {
		return id++;
	}
}
