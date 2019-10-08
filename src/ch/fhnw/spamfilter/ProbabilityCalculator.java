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
		final BigDecimal SpamAmount = BigDecimal.valueOf(m_wordStatistics.getTotalSpamAmount());
		final BigDecimal HamAmount = BigDecimal.valueOf(m_wordStatistics.getTotalHamAmount());

		List<BigDecimal> hamProbs = new ArrayList<>();
		List<BigDecimal> spamProbs = new ArrayList<>();
		for (String w : words) {
			// calculate probability P(w|H)
			BigDecimal hamProb = m_wordStatistics.getHamCount(w)
					.divide(HamAmount, 100, RoundingMode.HALF_DOWN);

			// calculate probability P(w|S)
			BigDecimal spamProb = m_wordStatistics.getSpamCount(w)
					.divide(SpamAmount, 100, RoundingMode.HALF_DOWN);

			hamProbs.add(hamProb);
			spamProbs.add(spamProb);
		}

		BigDecimal spamProbability = spamProbs.stream()
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		BigDecimal hamProbability = hamProbs.stream()
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		return spamProbability.divide(spamProbability.add(hamProbability), 100, RoundingMode.HALF_DOWN);
	}

}
