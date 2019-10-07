package ch.fhnw.spamfilter.io;

import java.io.File;
import java.util.ArrayList;

public class FileCollector {
	final static File FOLDER_LEARN_HAM = new File("ham-anlern");
	final static File FOLDER_LEARN_SPAM = new File("spam-anlern");
    final static File FOLDER_CALI_HAM = new File("ham-kallibrierung");
    final static File FOLDER_CALI_SPAM = new File("spam-kallibrierung");

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
		return collectMails(FOLDER_LEARN_HAM);
	}

	public static ArrayList<File> collectSpamLearningMails() {
		return collectMails(FOLDER_LEARN_SPAM);
	}
	
	public static ArrayList<File> collectHamCalibrationMails() {
        return collectMails(FOLDER_CALI_HAM);
    }

    public static ArrayList<File> collectSpamCalibrationMails() {
        return collectMails(FOLDER_CALI_SPAM);
    }

}