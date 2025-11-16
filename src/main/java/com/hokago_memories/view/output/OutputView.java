package com.hokago_memories.view.output;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.domain.UserRequest;

public class OutputView {

    public void printStartMessage() {
        System.out.println(
                OutputMessage.START_MESSAGE.getOutputMessage()
        );
    }

    public void printTierAndDjClass(UserRequest request, Tier tier, DjClass djClass) {
        System.out.println(
                OutputMessage.TIER_AND_DJCLASS.getOutputMessage(request.nickname(), request.button(), tier.tierName(),
                        djClass.getGrade().getDisplayName())
        );
    }
}
