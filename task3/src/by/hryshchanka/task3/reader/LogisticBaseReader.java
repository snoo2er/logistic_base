package by.hryshchanka.task3.reader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogisticBaseReader {
	private static final Logger LOGGER = LogManager.getLogger(LogisticBaseReader.class);
	private static final String INPUT_PATH = "data//input.txt";

	public static List<String> readBase() {
		List<String> baseLines = null;
		try {
			baseLines = Files.readAllLines(Paths.get(INPUT_PATH), StandardCharsets.UTF_8);
			if (baseLines.isEmpty()) {
				LOGGER.log(Level.FATAL, "File is empty");
				throw new RuntimeException();
			}
		} catch (IOException e) {
			LOGGER.log(Level.FATAL, "File not found");
			throw new RuntimeException();
		}
		return baseLines;
	}
}