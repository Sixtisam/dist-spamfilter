package ch.fhnw.spamfilter.io;

import java.io.File;
import java.util.ArrayList;

public class FileCollector {
	final static File FOLDER_HAM = new File("ham-anlern");
	final static File FOLDER_SPAM = new File("spam-anlern");

	public static ArrayList<File> collectMails(File folder) {
		ArrayList<File> files_list = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				collectMails(fileEntry);
			} else {
				files_list.add(fileEntry);

			}
		}
		return files_list;
	}

	public static ArrayList<File> collectHamLearningMails() {
		return collectMails(FOLDER_HAM);
	}

	public static ArrayList<File> collectSpamLearningMails() {
		return collectMails(FOLDER_SPAM);
	}

}