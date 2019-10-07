package ch.fhnw.spamfilter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProbabilityCalculator {
	private WordStatistics m_wordStatistics;

	public BigDecimal checkSpam(Set<String> words) {

		List<BigDecimal> hamAmounts = new ArrayList<>();
		// ham
		for (String w : words) {
			hamAmounts.add(m_wordStatistics.getHamCount(w));
		}

		List<BigDecimal> spamAmounts = new ArrayList<>();
		// spam
		for (String w : words) {
			spamAmounts.add(m_wordStatistics.getSpamCount(w));
		}

		BigDecimal spamProbability = hamAmounts.stream()
				.map(a -> a.divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount())))
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		BigDecimal hamProbability = spamAmounts.stream()
				.map(a -> a.divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount())))
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		return spamProbability.divide(spamProbability.add(hamProbability));
	}

}
