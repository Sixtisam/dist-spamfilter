package ch.fhnw.spamfilter.main;

/**
 * Starts the learned and afterwards the decider, which performs checking on the test amils.
 *
 */
public class Main {

    public static void main(String[] args) {
        SFLearner.main(args);
        //SFCalibrationHam.main(args);
        //SFCalibrationSpam.main(args);
        SFDecider.main(args);
    }

}
