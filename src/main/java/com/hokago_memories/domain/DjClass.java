package com.hokago_memories.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DjClass(
        double score,
        double rawTotal,
        double basicSum,
        double newSum
) {
    public DjClassGrade getGrade() {
        return DjClassGrade.findGrade(this.score);
    }

    @JsonProperty("grade")
    public String getGradeName() {
        return DjClassGrade.findGrade(this.score).getDisplayName();
    }

    @JsonProperty("displayScore")
    public String getDisplayScore() {
        return String.format("%.2f", this.score);
    }
}
