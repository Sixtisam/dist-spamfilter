package ch.fhnw.spamfilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MailParser {
	public static Set<String> parseMail(File mail) {
		try {
			return parseMail(new FileInputStream(mail));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("error parsing mail", e);
		}
	}

	public static Set<String> parseMail(InputStream in) {
		Set<String> words = new HashSet<>();
		try (Scanner scan = new Scanner(in)) {
			while (scan.hasNext() && scan.hasNextLine()) {
				String line = scan.nextLine();
				words.addAll(Arrays.asList(line.split(" ")));
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return words;
	}
}
