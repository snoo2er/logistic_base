package by.hryshchanka.task3.action;

public class TerminalIdAssigner {
	private static long id;

	public static long assignId() {
		return id++;
	}
}
