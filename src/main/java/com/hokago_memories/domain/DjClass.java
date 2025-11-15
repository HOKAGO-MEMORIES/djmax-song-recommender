package com.hokago_memories.domain;

public record DjClass(
        double score,
        double rawTotal,
        double basicSum,
        double newSum
) {
    public DjClassGrade getGrade() {
        return DjClassGrade.findGrade(this.score);
    }

    public String getDisplayScore() {
        return String.format("%.2f", this.score);
    }
}
