package com.emrecosar.numberoftrees;

import com.emrecosar.numberoftrees.generation.GenerateForest;
import com.emrecosar.numberoftrees.solution.NumberOfTrees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${field-of-view}")
    private int fieldOfView;

    @Value("${forest-file-name}")
    private String forestFileName;

    @Override
    public void run(String... args) throws Exception {

        NumberOfTrees numberOfTrees = new NumberOfTrees(forestFileName);
        double solution = numberOfTrees.execute(fieldOfView);
        logger.info("Solution : {}°", solution);

        logger.info("Asta la Vista!");

    }
}
