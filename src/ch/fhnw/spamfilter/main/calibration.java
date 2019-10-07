package ch.fhnw.spamfilter.main;

import ch.fhnw.spamfilter.io.FileCollector;
import ch.fhnw.spamfilter.io.WordStatisticsStorage;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ch.fhnw.spamfilter.MailParser;
import ch.fhnw.spamfilter.ProbabilityCalculator;
import ch.fhnw.spamfilter.WordStatistics;

public class calibration {

    public static void main(String[] args) {
        WordStatistics statistics = WordStatisticsStorage.readFromStorage();
        ProbabilityCalculator probCal = new ProbabilityCalculator(statistics);
        ArrayList<File> files_list = FileCollector.collectHamLearningMails();
        BigDecimal sumSpamProbability = new BigDecimal(0);
        BigDecimal numberOfMails =  new BigDecimal(files_list.size());
        BigDecimal minSpamProbability = new BigDecimal(1);
        BigDecimal maxSpamProbability =  new BigDecimal(0);
        
        for (File fileEntry : files_list) {
            Set<String> words = MailParser.parseMail(fileEntry);
            BigDecimal prob = probCal.checkSpam(words);
            if (minSpamProbability.compareTo(prob) > 0) {
                minSpamProbability = prob;
            }
            if (maxSpamProbability.compareTo(prob) < 0) { 
                maxSpamProbability = prob;
            }
            sumSpamProbability = sumSpamProbability.add(prob);
        }
        System.out.println(sumSpamProbability.divide(numberOfMails, 100, RoundingMode.HALF_DOWN).toPlainString());
        System.out.println(minSpamProbability.toPlainString());
        System.out.println(maxSpamProbability.toPlainString());
    }
}
