package ch.fhnw.spamfilter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordStatistics {
	private Map<String, BigDecimal> m_hamCount = new HashMap<String, BigDecimal>();
	private Map<String, BigDecimal> m_spamCount = new HashMap<String, BigDecimal>();

	public void addSpamWords(Set<String> words) {
		for(String w: words) {
			if(m_spamCount.get(w) != null) {
				m_spamCount.put(w, m_spamCount.get(w).add(BigDecimal.ONE));
			}else {
				m_spamCount.put(w, BigDecimal.ONE);
			}
			
			if(m_hamCount.get(w) == null) {
				m_hamCount.put(w, new BigDecimal("0.000000000000000000001"));
			}
		}
	}

	public void addHamWords(Set<String> words) {
		for(String w: words) {
			if(m_hamCount.get(w) != null) {
				m_hamCount.put(w, m_hamCount.get(w).add(BigDecimal.ONE));
			}else {
				m_hamCount.put(w, BigDecimal.ONE);
			}
			
			if(m_spamCount.get(w) == null) {
				m_spamCount.put(w, new BigDecimal("0.000000000000000000001"));
			}
		}
	}

	public BigDecimal getHamCount(String word) {
		return m_hamCount.get(word);
	}

	public BigDecimal getSpamCount(String word) {
		return m_spamCount.get(word);
	}
}
