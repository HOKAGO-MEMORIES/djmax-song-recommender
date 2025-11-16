package com.hokago_memories.controller;

import com.hokago_memories.service.PlayerInfoService;
import com.hokago_memories.view.input.InputView;
import com.hokago_memories.view.output.OutputVIew;

public class CliController {

    private final PlayerInfoService playerInfoService;
    private final InputView inputView;
    private final OutputVIew outputView;


    public CliController(PlayerInfoService playerInfoService, InputView inputView, OutputVIew outputVIew) {
        this.playerInfoService = playerInfoService;
        this.inputView = inputView;
        this.outputView = outputVIew;
    }

    public void run() {
        
    }
}
