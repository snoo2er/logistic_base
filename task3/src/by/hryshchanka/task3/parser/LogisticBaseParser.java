package by.hryshchanka.task3.parser;

import java.util.ArrayList;
import java.util.List;

public class LogisticBaseParser {
	
	public static ArrayList<String[]> createDataList(List<String> lines) {
		ArrayList<String[]> dataList = new ArrayList<>();
		for (String line : lines) {
			String[] data;
			String[] vanData = line.split("\\s+");
			data = new String[vanData.length];
			for (int i = 0; i < data.length; i++) {
				data[i] = vanData[i];
			}
			dataList.add(data);
		}
		return dataList;
	}
}