package com.hokago_memories.config;

import com.hokago_memories.controller.CliController;
import com.hokago_memories.domain.calculator.DjClassCalculator;
import com.hokago_memories.domain.calculator.DjPowerCalculator;
import com.hokago_memories.domain.calculator.TargetAccuracyCalculator;
import com.hokago_memories.domain.calculator.TheoreticalMaxCalculator;
import com.hokago_memories.domain.rule.DjPowerRules;
import com.hokago_memories.domain.validator.PatternAnalyzer;
import com.hokago_memories.infrastructure.api.NetworkClient;
import com.hokago_memories.repository.SongRepository;
import com.hokago_memories.repository.impl.JpaSongRepository;
import com.hokago_memories.service.PlayerInfoService;
import com.hokago_memories.service.SongDataInitializerService;
import com.hokago_memories.service.api.OpenApiService;
import com.hokago_memories.service.api.ProductionOpenApiService;
import com.hokago_memories.service.recommendation.ImprovementRecommender;
import com.hokago_memories.service.recommendation.NewSongRecommender;
import com.hokago_memories.service.recommendation.RecommendationService;
import com.hokago_memories.view.input.InputView;
import com.hokago_memories.view.output.OutputView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AppConfig {

    public CliController cliController() {
        return new CliController(playerInfoService(), recommendationService(), inputView(), outputVIew());
    }

    public PlayerInfoService playerInfoService() {
        return new PlayerInfoService(openApiService(), djClassCalculator());
    }

    public RecommendationService recommendationService() {
        return new RecommendationService(
                improvementRecommender(),
                newSongRecommender(),
                playerInfoService()
        );
    }

    public SongDataInitializerService songDataInitializerService() {
        return new SongDataInitializerService(networkClient(), songRepository());
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputVIew() {
        return new OutputView();
    }

    private OpenApiService openApiService() {
        return new ProductionOpenApiService(networkClient());
    }

    private DjClassCalculator djClassCalculator() {
        return new DjClassCalculator(theoreticalMaxCalculator().calculateAll());
    }

    private DjPowerCalculator djPowerCalculator() {
        return new DjPowerCalculator(djPowerRules());
    }

    private TargetAccuracyCalculator targetAccuracyCalculator() {
        return new TargetAccuracyCalculator(djPowerCalculator());
    }

    private TheoreticalMaxCalculator theoreticalMaxCalculator() {
        return new TheoreticalMaxCalculator(songRepository(), djPowerRules());
    }

    private ImprovementRecommender improvementRecommender() {
        return new ImprovementRecommender(songRepository(), djPowerCalculator());
    }

    private NewSongRecommender newSongRecommender() {
        return new NewSongRecommender(songRepository(), patternAnalyzer());
    }

    private PatternAnalyzer patternAnalyzer() {
        return new PatternAnalyzer(djPowerCalculator(), targetAccuracyCalculator());
    }

    private DjPowerRules djPowerRules() {
        return new DjPowerRules();
    }

    private SongRepository songRepository() {
        return new JpaSongRepository(entityManager());
    }

    private EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("v-archive-project");
        return emf.createEntityManager();
    }

    private NetworkClient networkClient() {
        return new NetworkClient();
    }
}
