package ch.fhnw.spamfilter.main;

import java.io.File;
import java.math.BigDecimal;
import java.util.Set;

import ch.fhnw.spamfilter.MailParser;
import ch.fhnw.spamfilter.ProbabilityCalculator;
import ch.fhnw.spamfilter.WordStatistics;
import ch.fhnw.spamfilter.io.WordStatisticsStorage;

public class SFDecider {
	public static final BigDecimal SPAM_PROBABILITY_TRHESHOLD = new BigDecimal("0.5");

	public static void main(String[] args) {
		WordStatistics statistics = WordStatisticsStorage.readFromStorage();

		Set<String> words = MailParser.parseMail(new File("spam-test/00043.548c447db5d9ba3f5546de96baa9b0e6"));

		ProbabilityCalculator calc = new ProbabilityCalculator(statistics);
		BigDecimal spamProb = calc.checkSpam(words);
		System.out.println("Calculated possibility of spam: " + spamProb.toPlainString());
		if (spamProb.compareTo(SPAM_PROBABILITY_TRHESHOLD) >= 0) {
			System.out.println("MAIL CLASSIFIED AS SPAM");
		}
	}
}
