package com.hokago_memories.view.output;

public enum OutputMessage {
    START_MESSAGE("닉네임과 버튼 수를 입력해주세요.\n입력 형식은 '(닉네임),(버튼수)' 입니다."),
    ;

    private final String outputMessage;

    OutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public String getOutputMessage(Object... args) {
        return String.format(outputMessage, args);
    }
}
