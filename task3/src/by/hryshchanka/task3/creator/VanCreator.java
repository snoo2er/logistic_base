package by.hryshchanka.task3.creator;

import java.util.ArrayList;

import by.hryshchanka.task3.action.VanIdAssigner;
import by.hryshchanka.task3.entity.Van;

public class VanCreator {
	private static final int BASE_TERMINAL_QUANTITY_INDEX = 0;
	private static final int VAN_LOAD_INDEX = 0;
	private static final int VAN_IS_PERISHABLE_INDEX = 1;
	private static final boolean LOADED = true;
	private static final boolean EMPTY = false;
	private static final boolean PERISHABLE = true;
	private static final boolean NOT_PERISHABLE = false;

	public static ArrayList<Van> createVanList(ArrayList<String[]> data) {
		ArrayList<Van> vanList = new ArrayList<>();
		data.remove(BASE_TERMINAL_QUANTITY_INDEX);
		for (String[] vanData : data) {
			if (vanData[VAN_LOAD_INDEX].equalsIgnoreCase("loaded")) {
				if (vanData[VAN_IS_PERISHABLE_INDEX].equalsIgnoreCase("perishable")) {
					vanList.add(new Van(VanIdAssigner.assignId(), LOADED, PERISHABLE));
				} else if (vanData[1].equalsIgnoreCase("notPerishable")) {
					vanList.add(new Van(VanIdAssigner.assignId(), LOADED, NOT_PERISHABLE));
				}
			} else if (vanData[VAN_LOAD_INDEX].equalsIgnoreCase("empty")) {
				vanList.add(new Van(VanIdAssigner.assignId(), EMPTY));
			}
		}
		return vanList;
	}
}
