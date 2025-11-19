package com.hokago_memories.domain.calculator;

import com.hokago_memories.domain.rule.DjPowerRules;

public class DjPowerCalculator {

    private final DjPowerRules djPowerRules;

    public DjPowerCalculator(DjPowerRules djPowerRules) {
        this.djPowerRules = djPowerRules;
    }

    public double calculate(String difficulty, int level, double score) {
        double maxPower = djPowerRules.getMaxPower(difficulty, level);

        if (maxPower <= 0 || score < 90.0) {
            return 0.0;
        }

        double ratio = calculateRatio(score);

        return ratio * maxPower;
    }

    private double calculateRatio(double x) {
        if (x < 90.0) {
            return 0.0;
        }

        // 100% (퍼펙트) 처리
        if (Math.abs(x - 100.0) < 1e-6) {
            return 1.0;
        }
        if (x > 100.0) {
            return 1.0; // 100 초과 시 100으로 간주
        }

        if (x <= 94.5) {
            // t = (24 / 135 * np.e ** (8 / 9 * (x - 95)) + 0.125)
            return (24.0 / 135.0) * Math.exp((8.0 / 9.0) * (x - 95.0)) + 0.125;
        }

        if (x < 95.5) {
            // t = cal(94.5) + (cal(95.5) - cal(94.5)) * (x - 94.5) + (x - 94.5) * (x - 95.5) / 30
            double val945 = calculateRatio(94.5);
            double val955 = calculateRatio(95.5);
            return val945 + (val955 - val945) * (x - 94.5) + (x - 94.5) * (x - 95.5) / 30.0;
        }

        if (x <= 96.5) {
            // t = (24 / 135 * np.e ** (2 / 3 * (x - 95)) + 0.125)
            return (24.0 / 135.0) * Math.exp((2.0 / 3.0) * (x - 95.0)) + 0.125;
        }

        // 96.5 초과 구간 공통 베이스 (Log 함수)
        // base = (80 / 297 * (np.log(x - 95) - np.log(5)) + 10 / 11)
        double base = (80.0 / 297.0) * (Math.log(x - 95.0) - Math.log(5.0)) + (10.0 / 11.0);

        if (x <= 97.5) {
            // t = base * (3 * x - 250.5) / 40
            return base * (3.0 * x - 250.5) / 40.0;
        }

        if (x <= 98.0) {
            // t = base * (x - 76.5) / 20
            return base * (x - 76.5) / 20.0;
        }

        if (x < 98.5) {
            // t = base * (3 * x - 186.5) / 100
            return base * (3.0 * x - 186.5) / 100.0;
        }

        if (x < 99.0) {
            // t = base * (x - 44) / 50
            return base * (x - 44.0) / 50.0;
        }

        // 99.0 <= x < 100.0
        // t = 8 / 27 * (np.log(x - 95) - np.log(5)) + 1
        return (8.0 / 27.0) * (Math.log(x - 95.0) - Math.log(5.0)) + 1.0;
    }
}
