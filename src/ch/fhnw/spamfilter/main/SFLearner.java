package ch.fhnw.spamfilter.main;

import ch.fhnw.spamfilter.MailParser;
import ch.fhnw.spamfilter.WordStatistics;
import ch.fhnw.spamfilter.io.FileCollector;
import ch.fhnw.spamfilter.io.WordStatisticsStorage;

public class SFLearner {

	public static void main(String[] args) {
		WordStatistics statistics = WordStatisticsStorage.readFromStorage();

		FileCollector.collectHamLearningMails()
				.stream()
				.map(MailParser::parseMail)
				.forEach(words -> {
					statistics.addHamMail(words);
					System.out.println("Ham-Mail learned");
				});

		FileCollector.collectHamLearningMails()
				.stream()
				.map(MailParser::parseMail)
				.forEach(words -> {
					statistics.addSpamEmail(words);
					System.out.println("Spam-Mail learned");
				});
		WordStatisticsStorage.writeToStorage(statistics);
	}
}
