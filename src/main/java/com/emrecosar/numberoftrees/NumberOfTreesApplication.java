package com.emrecosar.numberoftrees;

import com.emrecosar.numberoftrees.generation.GenerateForest;
import com.emrecosar.numberoftrees.helper.Helper;
import com.emrecosar.numberoftrees.model.ApplicationProperties;
import com.emrecosar.numberoftrees.solution.NumberOfTrees;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class NumberOfTreesApplication {

    private static final Logger logger = Logger.getLogger(NumberOfTreesApplication.class.toString());
    private static ApplicationProperties properties;

    public static void main(String[] args) throws IOException {

        validateArgs(args);
        Helper helper = new Helper();
        loadProperties(helper);
        run(args[0]);

    }

    public static void run(String command) throws IOException {

        logger.info("Number-of-Trees application started!");

        if (command.equalsIgnoreCase(properties.getGenerateForest())) {
            GenerateForest.execute(properties.getForestSize(), properties.getForestFileName());
        } else if (command.equalsIgnoreCase(properties.getNumberOfTrees())) {
            NumberOfTrees numberOfTrees = new NumberOfTrees(properties.getForestFileName());
            double solution = numberOfTrees.execute(properties.getFieldOfView());
            logger.info(String.format("Solution : {%s}°", solution));
        } else {
            logger.severe("invalid parameters!");
            System.exit(1);
        }

        logger.info("Asta la Vista!");

    }

    private static void validateArgs(String[] args) throws IllegalArgumentException {
        Optional.of(args[0]).orElseThrow(() -> new IllegalArgumentException("Please provide one and valid command!"));
    }

    private static void loadProperties(Helper helper) throws IOException {
        properties = helper.loadProperties()
                .orElseThrow(() -> new IOException("Exception on reading application.properties"));
    }

}
