package ch.fhnw.spamfilter.main;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.fhnw.spamfilter.ProbabilityCalculator;
import ch.fhnw.spamfilter.WordStatistics;

/**
 * Test case which checks if our formular is correctly implemented
 * 
 * http://www.math.kit.edu/ianm4/~ritterbusch/seite/spam/de
 * 
 * @author Samuel Keusch, Florian Schärer, Stefan Landös
 *
 */
public class SFSmallTest {
	public static void main(String[] args) {
		WordStatistics wordStatistics = new WordStatistics();

		for (int i = 0; i < 30; i++) {
			wordStatistics.addHamMail(Collections.singleton("haben"));
		}

		for (int i = 0; i < 3; i++) {
			wordStatistics.addHamMail(Collections.singleton("online"));
		}

		for (int i = 0; i < 7; i++) {
			wordStatistics.addSpamEmail(Collections.singleton("haben"));
		}
		for (int i = 0; i < 8; i++) {
			wordStatistics.addSpamEmail(Collections.singleton("online"));
		}

		ProbabilityCalculator calc = new ProbabilityCalculator(wordStatistics);

		Set<String> words = new HashSet<>();
		words.add("haben");
		words.add("online");

		System.out.println(calc.checkSpam(words));
	}
}
