package ch.fhnw.spamfilter.main;

public class Main {

    public static void main(String[] args) {
        SFLearner.main(args);
        //SFCalibrationHam.main(args);
        //SFCalibrationSpam.main(args);
        SFDecider.main(args);
    }

}
