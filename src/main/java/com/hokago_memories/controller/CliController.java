package com.hokago_memories.controller;

import com.hokago_memories.domain.DjClass;
import com.hokago_memories.domain.Tier;
import com.hokago_memories.domain.UserRequest;
import com.hokago_memories.service.PlayerInfoService;
import com.hokago_memories.util.Splitter;
import com.hokago_memories.util.parser.StringParser;
import com.hokago_memories.view.input.InputValidator;
import com.hokago_memories.view.input.InputView;
import com.hokago_memories.view.output.OutputView;
import java.util.List;

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
        outputView.printStartMessage();
        String input = inputView.readInput();
        InputValidator.validateInput(input);
        List<String> inputs = Splitter.split(input);
        UserRequest request = new UserRequest(inputs.getFirst(), StringParser.ParseStringToInt(inputs.getLast()));
        Tier tier = playerInfoService.getUserTier(request);
        DjClass djClass = playerInfoService.getDjClass(request);
        outputView.printTierAndDjClass(request, tier, djClass);
    }
}
