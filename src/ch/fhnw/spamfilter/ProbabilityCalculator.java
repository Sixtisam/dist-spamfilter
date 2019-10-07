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
            BigDecimal count = m_wordStatistics.getHamCount(w)
                    .divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount()), 100, RoundingMode.HALF_DOWN);
            BigDecimal count2 = m_wordStatistics.getSpamCount(w)
                    .divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount()), 100, RoundingMode.HALF_DOWN);
            if (count.compareTo(new BigDecimal("0.00000001")) > 0 || count2.compareTo(new BigDecimal("0.00000001")) > 0 ) {
                hamAmounts.add(count);
                spamAmounts.add(count2);
            }
        }

        BigDecimal spamProbability = spamAmounts.stream()
//                .map(a -> a.divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount()), 100, RoundingMode.HALF_DOWN))
//                .filter(a -> a.compareTo(new BigDecimal("0.7")) == -1)
                .reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

        BigDecimal hamProbability = hamAmounts.stream()
//                .map(a -> a.divide(BigDecimal.valueOf(m_wordStatistics.getTotalAmount()), 100, RoundingMode.HALF_DOWN))
//                .filter(a -> a.compareTo(new BigDecimal("0.7")) == -1)
                .reduce(BigDecimal.ONE, (a, b) -> a.multiply(b));

        return spamProbability.divide(spamProbability.add(hamProbability), 100, RoundingMode.HALF_DOWN);
    }

}
