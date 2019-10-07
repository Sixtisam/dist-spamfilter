package ch.fhnw.spamfilter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

public class WordStatistics implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Total amount of mails which were learned from
	 */
	private long m_totalAmount;
	/**
	 * For already found word, the number of HAM mails in which the word appeared.
	 */
	private HashMap<String, BigDecimal> m_hamCount = new HashMap<String, BigDecimal>();
	/**
	 * For already found word, the number of SPAM mails in which the word appeared.
	 */
	private HashMap<String, BigDecimal> m_spamCount = new HashMap<String, BigDecimal>();

	public void addSpamEmail(Set<String> words) {
		m_totalAmount++;
		for (String w : words) {
			if (m_spamCount.get(w) != null) {
				m_spamCount.put(w, m_spamCount.get(w).add(BigDecimal.ONE));
			} else {
				m_spamCount.put(w, BigDecimal.ONE);
			}
		}
	}

	public void addHamMail(Set<String> words) {
		m_totalAmount++;
		for (String w : words) {
			if (m_hamCount.get(w) != null) {
				m_hamCount.put(w, m_hamCount.get(w).add(BigDecimal.ONE));
			} else {
				m_hamCount.put(w, BigDecimal.ONE);
			}
		}
	}

	/**
	 * A value for words that are not yet in HAM or SPAM statistics. It can't be 0
	 * because otherwise, probability would become 0.
	 */
	public final static BigDecimal NEAR_ZERO = new BigDecimal("0.0000000000000000000001");

	/**
	 * Returns the number of HAM mails in which the word 'word' was found. In case
	 * word is not already known, a value near zero is returned to prevent getting
	 * zero probability.
	 */
	public BigDecimal getHamCount(String word) {
		return m_hamCount.getOrDefault(word, NEAR_ZERO);
	}

	/**
	 * * Returns the number of SPAM mails in which the word 'word' was found. In
	 * case word is not already known, a value near zero is returned to prevent
	 * getting zero probability.
	 * 
	 */
	public BigDecimal getSpamCount(String word) {
		return m_spamCount.getOrDefault(word, NEAR_ZERO);
	}

	public long getTotalAmount() {
		return m_totalAmount;
	}
}
