package ch.fhnw.spamfilter.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ch.fhnw.spamfilter.WordStatistics;

public class WordStatisticsStorage {
	public static final String STORAGE_PATH = "word_statistics.obj";

	/**
	 * Reads the word statistics from the filesystem
	 * 
	 * @return the read word statistics or an empty, if it does not exists yet.
	 */
	public static WordStatistics readFromStorage() {
		if (!(new File(STORAGE_PATH)).exists()) {
			return new WordStatistics();
		}
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(STORAGE_PATH))) {
			Object obj = inputStream.readObject();
			if (obj instanceof WordStatistics) {
				return (WordStatistics) obj;
			} else {
				throw new RuntimeException("unexpected file content " + obj.getClass().getSimpleName());
			}
		} catch (Exception e) {
			throw new RuntimeException("error reading file", e);
		}
	}

	/**
	 * Writes the passed word statistics to the filesystem
	 * 
	 * @param statistics
	 */
	public static void writeToStorage(WordStatistics statistics) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STORAGE_PATH))) {
			out.writeObject(statistics);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException("error writing file", e);
		}
	}
}
