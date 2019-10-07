package ch.fhnw.spamfilter.main;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Set;

import ch.fhnw.spamfilter.MailParser;
import ch.fhnw.spamfilter.ProbabilityCalculator;
import ch.fhnw.spamfilter.WordStatistics;
import ch.fhnw.spamfilter.io.FileCollector;
import ch.fhnw.spamfilter.io.WordStatisticsStorage;

public class SFCalibration {

	public static void main(String[] args) {
		WordStatistics statistics = WordStatisticsStorage.readFromStorage();
		ProbabilityCalculator probCal = new ProbabilityCalculator(statistics);
		ArrayList<File> files_list = FileCollector.collectSpamLearningMails();
		BigDecimal sumSpamProbability = new BigDecimal(0);
		BigDecimal numberOfMails = new BigDecimal(files_list.size());
		BigDecimal minSpamProbability = new BigDecimal(1);
		BigDecimal maxSpamProbability = new BigDecimal(0);

		for (File fileEntry : files_list) {
			Set<String> words = MailParser.parseMail(fileEntry);
			BigDecimal prob = probCal.checkSpam(words);

			// keep min probability
			if (minSpamProbability.compareTo(prob) > 0) {
				minSpamProbability = prob;
			}

			// keep max probability
			if (maxSpamProbability.compareTo(prob) < 0) {
				maxSpamProbability = prob;
			}
			sumSpamProbability = sumSpamProbability.add(prob);
		}
		System.out.println(sumSpamProbability.divide(numberOfMails, 100, RoundingMode.HALF_DOWN).toPlainString());
		System.out.println(minSpamProbability.toPlainString());
		System.out.println(maxSpamProbability.toPlainString());
	}
}
