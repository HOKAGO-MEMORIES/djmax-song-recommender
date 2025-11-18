package com.hokago_memories.domain.logic;

public class TargetAccuracyCalculator {

    private final DjPowerCalculator calculator;

    public TargetAccuracyCalculator(DjPowerCalculator calculator) {
        this.calculator = calculator;
    }

    public double findRequiredAccuracy(String difficulty, int level, double targetPower) {
        double maxPower = calculator.calculate(difficulty, level, 100.0);
        if (targetPower > maxPower) {
            return -1.0;
        }

        return binarySearch(90.0, 100.0, targetPower, difficulty, level);
    }

    private double binarySearch(double low, double high, double targetPower, String difficulty, int level) {
        double result = -1.0;

        for (int i = 0; i < 20; i++) {
            double mid = (low + high) / 2;

            double calculatedPower = calculator.calculate(difficulty, level, mid);

            if (calculatedPower >= targetPower) {
                result = mid;
                high = mid;
            } else {
                low = mid;
            }
        }

        return result;
    }
}
