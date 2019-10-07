package ch.fhnw.spamfilter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProbabilityCalculator {
	private WordStatistics m_wordStatistics;

	public ProbabilityCalculator(WordStatistics statistics) {
		m_wordStatistics = statistics;
	}

	public BigDecimal checkSpam(Set<String> words) {
		List<BigDecimal> hamAmounts = new ArrayList<>();
		List<BigDecimal> spamAmounts = new ArrayList<>();
		for (String w : words) {
			BigDecimal count = m_wordStatistics.getHamCount(w);
			if (count != null) {
				hamAmounts.add(count);
			}
			BigDecimal count2 = m_wordStatistics.getSpamCount(w);
			if (count2 != null) {
				spamAmounts.add(count2);
			}
		}

		BigDecimal spamProbability = spamAmounts.stream()
				.map(a -> a.divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount()), 100, RoundingMode.HALF_DOWN))
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		BigDecimal hamProbability = hamAmounts.stream()
				.map(a -> a.divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount()), 100, RoundingMode.HALF_DOWN))
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		return spamProbability.divide(spamProbability.add(hamProbability), 100, RoundingMode.HALF_DOWN);
	}

}
