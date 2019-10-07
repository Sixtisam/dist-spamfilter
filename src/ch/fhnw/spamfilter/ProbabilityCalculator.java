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
		final BigDecimal mailAmount = BigDecimal.valueOf(m_wordStatistics.getTotalAmount());

		List<BigDecimal> hamProbs = new ArrayList<>();
		List<BigDecimal> spamProbs = new ArrayList<>();
		for (String w : words) {
			// calculate probability P(w|H)
			BigDecimal hamProb = m_wordStatistics.getHamCount(w)
					.divide(mailAmount, 100, RoundingMode.HALF_DOWN);

			// calculate probability P(w|S)
			BigDecimal spamProb = m_wordStatistics.getSpamCount(w)
					.divide(mailAmount, 100, RoundingMode.HALF_DOWN);

			hamProbs.add(hamProb);
			spamProbs.add(spamProb);
		}

		BigDecimal spamProbability = spamProbs.stream()
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

		BigDecimal hamProbability = hamProbs.stream()
				.reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

//		System.out.println("SpamProb: " + spamProbability.toPlainString());
//		System.out.println("HamProb: " + hamProbability.toPlainString());
		return spamProbability.divide(spamProbability.add(hamProbability), 100, RoundingMode.HALF_DOWN);
	}

}
