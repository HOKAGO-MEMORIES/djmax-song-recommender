package com.hokago_memories.controller;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.domain.UserRequest;
import com.hokago_memories.exception.TierNotFoundException;
import com.hokago_memories.exception.UserNotFoundException;
import com.hokago_memories.service.PlayerInfoService;
import com.hokago_memories.util.Splitter;
import com.hokago_memories.util.parser.StringParser;
import com.hokago_memories.view.input.InputValidator;
import com.hokago_memories.view.input.InputView;
import com.hokago_memories.view.output.OutputView;
import java.util.List;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

public class CliController {

    private final PlayerInfoService playerInfoService;
    private final InputView inputView;
    private final OutputView outputView;


    public CliController(PlayerInfoService playerInfoService, InputView inputView, OutputView outputVIew) {
        this.playerInfoService = playerInfoService;
        this.inputView = inputView;
        this.outputView = outputVIew;
    }

    public void run() {
        try {
            UserRequest request = getUserInfo();
            Tier tier = playerInfoService.getUserTier(request);
            DjClass djClass = playerInfoService.getDjClass(request);
            outputView.printTierAndDjClass(request, tier, djClass);
            
        } catch (Exception e) {
            outputView.printError(e.getMessage());
        }
    }

    private UserRequest getUserInfo() {
        while (true) {
            try {
                outputView.printStartMessage();
                String input = inputView.readInput();

                // 형식 검증
                InputValidator.validateInput(input);
                List<String> inputs = Splitter.split(input);
                UserRequest request = new UserRequest(inputs.getFirst(),
                        StringParser.ParseStringToInt(inputs.getLast()));

                // API 존재 검증
                playerInfoService.getUserTier(request);

                return request;

            } catch (IllegalIdentifierException | UserNotFoundException | TierNotFoundException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
