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

/**
 * Calibration for ham emails checks all calibration emails and prints out all values which are not 1
 * Also shows the average min and max.
 *
 */
public class SFCalibrationHam {

	public static void main(String[] args) {
		WordStatistics statistics = WordStatisticsStorage.readFromStorage();
		ProbabilityCalculator probCal = new ProbabilityCalculator(statistics);
		ArrayList<File> files_list = FileCollector.collectHamCalibrationMails();
		BigDecimal sumSpamProbability = new BigDecimal(0);
		BigDecimal numberOfMails = new BigDecimal(files_list.size());
		BigDecimal minSpamProbability = new BigDecimal(1);
		BigDecimal maxSpamProbability = new BigDecimal(0);

		System.out.println("Alle Wahrscheinlichkeiten der 'ham-kalibrierung'-Mails, welche ungleich 0");
		System.out.println("------------------------------------------------");
		for (File fileEntry : files_list) {
			Set<String> words = MailParser.parseMail(fileEntry);
			BigDecimal prob = probCal.checkSpam(words);
			if (prob.compareTo(BigDecimal.ZERO) != 0) {
				System.out.println(prob.toPlainString());
			}
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

		System.out.println("------------------------------------------------");
		System.out.println("Aggregiert");
		System.out.println(
				"AVG: " + sumSpamProbability.divide(numberOfMails, 100, RoundingMode.HALF_DOWN).toPlainString());
		System.out.println("MIN: " + minSpamProbability.toPlainString());
		System.out.println("MAX: " + maxSpamProbability.toPlainString());
	}
}
