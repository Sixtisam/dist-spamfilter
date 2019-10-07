package ch.fhnw.spamfilter.main;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import ch.fhnw.spamfilter.MailParser;
import ch.fhnw.spamfilter.ProbabilityCalculator;
import ch.fhnw.spamfilter.WordStatistics;
import ch.fhnw.spamfilter.io.FileCollector;
import ch.fhnw.spamfilter.io.WordStatisticsStorage;

public class SFDecider {
	public static final BigDecimal SPAM_PROBABILITY_TRHESHOLD = new BigDecimal("0.9");

	public static void main(String[] args) {
		WordStatistics statistics = WordStatisticsStorage.readFromStorage();
		ProbabilityCalculator calc = new ProbabilityCalculator(statistics);

		int wrong = 0; // counts wrongly classified mails
		int correct = 0; // counts correctly classified mails

		System.out.println("Running test for spam mails....");
		// check each spam mail and increment the corresponding counter
		List<File> spamFiles = FileCollector.collectSpamTestMails();
		for (File file : spamFiles) {
			if (fulfillsThreshold(calc, file)) {
				correct++;
			} else {
				wrong++;
			}
		}
		System.out.println("Classified " + spamFiles.size() + " spam mails");
		System.out.println(correct + " correct");
		System.out.println(wrong + " wrong");
		System.out.println((correct / (double) spamFiles.size()) * 100 + "% Success Rate");
		System.out.println("----------------------------------------------------------");
		
		int correct2 = 0;
		int wrong2 = 0;
		
		System.out.println("Running test for ham mails......");
		// check each ham mail and increment the corresponding counter
		List<File> hamFiles = FileCollector.collectHamTestMails();
		for (File file : hamFiles) {
			if (fulfillsThreshold(calc, file)) {
				wrong2++;
			} else {
				correct2++;
			}
		}
		System.out.println("Classified " + hamFiles.size() + " ham mails");
		System.out.println(correct2 + " correct");
		System.out.println(wrong2 + " wrong");
		System.out.println((correct2 / (double) hamFiles.size()) * 100 + "% Success Rate");

		System.out.println("----------------------------------------------------------");
		System.out.println("Spam + Ham Mails");
		System.out.println((correct + correct2) + " correct");
		System.out.println((wrong + wrong2) + " wrong");
		System.out.println((correct + correct2) / ((double) hamFiles.size() + spamFiles.size()) * 100 + "% Success Rate");
	}

	protected static boolean fulfillsThreshold(ProbabilityCalculator calc, File mail) {
		BigDecimal prob = calc.checkSpam(MailParser.parseMail(mail));
		return prob.compareTo(SPAM_PROBABILITY_TRHESHOLD) >= 0;
	}
}
