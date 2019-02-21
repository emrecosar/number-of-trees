package com.emrecosar.numberoftrees;

import com.emrecosar.numberoftrees.generation.GenerateForest;
import com.emrecosar.numberoftrees.solution.NumberOfTrees;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(Application.class.toString());

    @Value("${field-of-view}")
    private int fieldOfView;

    @Value("${forest-file-name}")
    private String forestFileName;

    @Value("${forest-size}")
    private int forestSize;


    @Value("${parameter}")
    private String parameter;

    @Value("${generate-forest}")
    private String generateForest;

    @Value("${number-of-trees}")
    private String numberOfTrees;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Number-of-Trees application started!");

        String parameter = System.getProperty("command");

        if(parameter == null || parameter.isEmpty()){
            logger.severe("parameter is wrong!");
            return;
        }

        if(parameter.equalsIgnoreCase(generateForest)){
            GenerateForest.execute(forestSize, forestFileName);
        } else if(parameter.equalsIgnoreCase(numberOfTrees)) {
            NumberOfTrees numberOfTrees = new NumberOfTrees(forestFileName);
            double solution = numberOfTrees.execute(fieldOfView);
            logger.info(String.format("Solution : {%s}°", solution));
        } else {
            logger.severe("invalid parameters!");
            System.exit(1);
        }

        logger.info("Asta la Vista!");

    }
}
