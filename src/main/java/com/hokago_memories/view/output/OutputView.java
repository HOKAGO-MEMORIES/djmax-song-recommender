package com.hokago_memories.view.output;

import com.hokago_memories.domain.Tier;
import com.hokago_memories.dto.request.UserRequest;
import com.hokago_memories.dto.response.ImprovementRecommendation;
import com.hokago_memories.dto.response.NewSongRecommendation;
import java.util.List;

public class OutputView {

    public void printStartMessage() {
        System.out.println(
                OutputMessage.START_MESSAGE.getOutputMessage()
        );
    }

    public void printTierAndDjClass(UserRequest request, Tier tier, String gradeName) {
        System.out.println(
                OutputMessage.TIER_AND_DJCLASS.getOutputMessage(request.nickname(), request.button(), tier.tierName(),
                        gradeName)
        );
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }


    public void printRecommendations(List<ImprovementRecommendation> recommendations,
                                     List<NewSongRecommendation> newSongRecommendations) {
        printImprovementRecommendation(recommendations);
        printNewSongRecommendation(newSongRecommendations);
    }

    private void printImprovementRecommendation(List<ImprovementRecommendation> recommendations) {
        System.out.println(OutputMessage.RECOMMEND_IMPROVEMENT_TITLE.getOutputMessage());

        if (recommendations.isEmpty()) {
            System.out.println(OutputMessage.NO_RECOMMENDATION.getOutputMessage());
            return;
        }

        for (ImprovementRecommendation rec : recommendations) {
            System.out.println(
                    OutputMessage.RECOMMEND_IMPROVEMENT_FORMAT.getOutputMessage(
                            rec.difficulty(),
                            rec.level(),
                            rec.title(),
                            rec.currentScore(),
                            rec.currentDjPower(),
                            rec.targetScore(),
                            rec.powerIncrease()
                    ));
        }
    }

    private void printNewSongRecommendation(List<NewSongRecommendation> newSongRecommendations) {
        System.out.println(OutputMessage.RECOMMEND_NEW_SONG_TITLE.getOutputMessage());

        if (newSongRecommendations.isEmpty()) {
            System.out.println(OutputMessage.NO_RECOMMENDATION.getOutputMessage());
            return;
        }

        for (NewSongRecommendation rec : newSongRecommendations) {
            System.out.println(
                    OutputMessage.RECOMMEND_NEW_SONG_FORMAT.getOutputMessage(
                            rec.category(),
                            rec.title(),
                            rec.difficulty(),
                            rec.level(),
                            rec.targetScore(),
                            rec.expectedDjPower(),
                            rec.gapOverCutoff()
                    ));
        }
    }
}
