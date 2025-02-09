package ch.fhnw.spamfilter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

/**
 * Statistics for word appearance in emails
 *
 */
public class WordStatistics implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Amount of spam emails learned from
	 */
	private long m_totalSpamAmount;
	/**
	 * Amount of ham emails learned from.
	 */
	private long m_totalHamAmount;
	/**
	 * For every word, the number of HAM mails in which the word appeared.
	 */
	private HashMap<String, BigDecimal> m_hamCount = new HashMap<String, BigDecimal>();
	/**
	 * For every word, the number of SPAM mails in which the word appeared.
	 */
	private HashMap<String, BigDecimal> m_spamCount = new HashMap<String, BigDecimal>();

	/**
	 * Add an email classified as "spam" to the statistics
	 * @param words
	 */
	public void addSpamMail(Set<String> words) {
		m_totalSpamAmount++;
		for (String w : words) {
			if (m_spamCount.get(w) != null) {
				m_spamCount.put(w, m_spamCount.get(w).add(BigDecimal.ONE));
			} else {
				m_spamCount.put(w, BigDecimal.ONE);
			}
		}
	}

	/**
	 * Add an email classified as "spam" to the statistics
	 * @param words
	 */
	public void addHamMail(Set<String> words) {
		m_totalHamAmount++;
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
	public final static BigDecimal ALPHA = new BigDecimal("0.0000000000000000000001");

	/**
	 * Returns the number of HAM mails in which the word 'word' was found. In case
	 * the word is not already known, ALPHA is returned to prevent
	 * zero probability.
	 */
	public BigDecimal getHamCount(String word) {
		return m_hamCount.getOrDefault(word, ALPHA);
	}

	/**
	 * * Returns the number of SPAM mails in which the word 'word' was found. In
	 * case the word is not already known, ALPHA is returned to prevent
	 * getting zero probability.
	 * 
	 */
	public BigDecimal getSpamCount(String word) {
		return m_spamCount.getOrDefault(word, ALPHA);
	}

	public long getTotalSpamAmount() {
		return m_totalSpamAmount;
	}
	
	public long getTotalHamAmount() {
        return m_totalHamAmount;
    }
}
