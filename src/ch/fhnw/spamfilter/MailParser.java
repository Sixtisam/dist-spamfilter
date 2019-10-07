package ch.fhnw.spamfilter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;

public class MailParser {
	public static Set<String> parseMail(File mail) {
	    Set<String> words = new HashSet<>();
        try {
            Scanner in = new Scanner(mail);
            while(in.hasNextLine()){
                String line = in.nextLine();
                words.addAll(Arrays.asList(line.split("\\s+")));
            }
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return words;
	}
}
