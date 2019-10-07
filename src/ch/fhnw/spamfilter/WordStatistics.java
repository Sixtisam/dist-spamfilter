package ch.fhnw.spamfilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordStatistics {
	private Map<String, Integer> m_hamCount = new HashMap<String, Integer>();
	private Map<String, Integer> m_spamCount = new HashMap<String, Integer>();

	public void addSpamWords(Set<String> words) {

	}

	public void addHamWords(Set<String> words) {

	}

	public int getHamCount(String word) {
		return 0;
	}

	public int getSpamCount(String word) {
		return 0;
	}
}
