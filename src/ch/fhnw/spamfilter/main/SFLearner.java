package ch.fhnw.spamfilter.main;

import java.io.File;
import java.util.List;

import ch.fhnw.spamfilter.MailParser;
import ch.fhnw.spamfilter.WordStatistics;
import ch.fhnw.spamfilter.io.FileCollector;
import ch.fhnw.spamfilter.io.WordStatisticsStorage;

public class SFLearner {

	public static void main(String[] args) {
		WordStatistics statistics = new WordStatistics();

		// learn all ham emails
		List<File> hamFiles = FileCollector.collectHamLearningMails();
		hamFiles.stream()
				.map(MailParser::parseMail)
				.forEach(words -> {
					statistics.addHamMail(words);
				});
		System.out.println("Learned " + hamFiles.size() + " ham emails");

		// learn all spam emails
		List<File> spamFiles = FileCollector.collectSpamLearningMails();
		FileCollector.collectSpamLearningMails()
				.stream()
				.map(MailParser::parseMail)
				.forEach(words -> {
					statistics.addSpamEmail(words);
				});
		System.out.println("Learned " + spamFiles.size() + " spam emails");
		
		// write learned data 
		WordStatisticsStorage.writeToStorage(statistics);
	}
}
