package com.hokago_memories.dto.internal;

public record TheoreticalMax(
        double b4,
        double b5,
        double b6,
        double b8
) {

    public double getForButton(int button) {
        return switch (button) {
            case 4 -> b4;
            case 5 -> b5;
            case 6 -> b6;
            case 8 -> b8;
            default -> throw new IllegalArgumentException("잘못된 버튼 " + button);
        };
    }
}
