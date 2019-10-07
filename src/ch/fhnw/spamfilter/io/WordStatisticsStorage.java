package ch.fhnw.spamfilter.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ch.fhnw.spamfilter.WordStatistics;

public class WordStatisticsStorage {
	public static final String STORAGE_PATH = "word_statistics.obj";

	public static WordStatistics readFromStorage() {
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

	public static void writeToStorage(WordStatistics statistics) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STORAGE_PATH))) {
			out.writeObject(statistics);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException("error writing file", e);
		}
	}
}
