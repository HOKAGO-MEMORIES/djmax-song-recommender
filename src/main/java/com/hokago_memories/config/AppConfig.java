package com.hokago_memories.config;

import com.hokago_memories.controller.CliController;
import com.hokago_memories.controller.WebController;
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
import java.util.HashMap;
import java.util.Map;

public class AppConfig {

    public CliController cliController() {
        return new CliController(playerInfoService(), recommendationService(), inputView(), outputVIew());
    }

    public WebController webController() {
        return new WebController(playerInfoService(), recommendationService());
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
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        String dbUser = System.getenv("JDBC_DATABASE_USERNAME");
        String dbPassword = System.getenv("JDBC_DATABASE_PASSWORD");

        Map<String, String> properties = new HashMap<>();

        if (dbUrl != null && !dbUrl.isEmpty()) {
            if (dbUrl.startsWith("postgres://")) {
                dbUrl = dbUrl.replace("postgres://", "jdbc:postgresql://");
            } else if (dbUrl.startsWith("postgresql://")) {
                dbUrl = dbUrl.replace("postgresql://", "jdbc:postgresql://");
            }

            properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("jakarta.persistence.jdbc.url", dbUrl);

            if (dbUser != null && !dbUser.isEmpty()) {
                properties.put("jakarta.persistence.jdbc.user", dbUser);
            }
            if (dbPassword != null && !dbPassword.isEmpty()) {
                properties.put("jakarta.persistence.jdbc.password", dbPassword);
            }

            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("hibernate.hbm2ddl.auto", "update");
        } else {
            // 로컬 테스트용 (H2 데이터베이스)
            properties.put("jakarta.persistence.jdbc.driver", "org.h2.Driver");
            properties.put("jakarta.persistence.jdbc.url", "jdbc:h2:file:./v-archive-db");
            properties.put("jakarta.persistence.jdbc.user", "user");
            properties.put("jakarta.persistence.jdbc.password", "");
            properties.put("hibernate.hbm2ddl.auto", "update");
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("v-archive-project", properties);
        return emf.createEntityManager();
    }

    private NetworkClient networkClient() {
        return new NetworkClient();
    }
}
