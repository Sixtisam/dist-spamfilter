package ch.fhnw.spamfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProbabilityCalculator {
	private long m_totalMailAmount;
	private WordStatistics m_wordStatistics;

	public double checkSpam(Set<String> words) {

		List<Integer> hamAmounts = new ArrayList<>();
		// ham
		for (String w : words) {
			hamAmounts.add(m_wordStatistics.getHamCount(w));
		}

		List<Integer> spamAmounts = new ArrayList<>();
		// spam
		for (String w : words) {
			spamAmounts.add(m_wordStatistics.getSpamCount(w));
		}

		hamAmounts.stream().mapToDouble(a -> a / (double) m_totalMailAmount);
		spamAmounts.stream().mapToDouble(a -> a / (double) m_totalMailAmount);

		// divide through total amount

		double spamProbability = spamAmounts.stream().reduce(1, (a, b) -> a * b);
		double hamProbability = hamAmounts.stream().reduce(1, (a, b) -> a * b);

		return spamProbability / (spamProbability + hamProbability);
	}

}
