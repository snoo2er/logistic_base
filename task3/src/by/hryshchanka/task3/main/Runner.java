package by.hryshchanka.task3.main;

import java.util.ArrayList;
import java.util.List;

import by.hryshchanka.task3.creator.LogisticBaseCreator;
import by.hryshchanka.task3.creator.VanCreator;
import by.hryshchanka.task3.entity.Van;
import by.hryshchanka.task3.parser.LogisticBaseParser;
import by.hryshchanka.task3.reader.LogisticBaseReader;

public class Runner {
	public static void main(String[] args) {
		List<String> lines = LogisticBaseReader.readBase();
		ArrayList<String[]> dataList = LogisticBaseParser.createDataList(lines);
		LogisticBaseCreator.createLogisticBase(dataList);
		
		ArrayList<Van> vanList = VanCreator.createVanList(dataList);
		for (Van van : vanList) {
			van.start();
		}
	}
}
