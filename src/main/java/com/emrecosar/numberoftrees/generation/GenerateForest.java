package com.emrecosar.numberoftrees.generation;

import com.emrecosar.numberoftrees.model.Tree;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateForest {

    private static final Logger logger = Logger.getLogger(GenerateForest.class.toString());
    private static final SecureRandom sr = new SecureRandom();

    private static final double MIN = -1000;
    private static final double MAX = 1000;

    public static void execute(int forestSize, String forestFileName) throws IOException {

        logger.info("Your forest '" + forestFileName + "' generation started!");

        List<Tree> forest = IntStream.range(0, forestSize - 1)
                .mapToObj(i -> {
                    Tree tree = new Tree(generatePoint(), generatePoint());
                    return tree;
                })
                .collect(Collectors.toList());
        try {
            new ObjectMapper().writer()
                    .writeValue(new File(forestFileName), forest);
        } catch (Exception e){
            logger.severe("Your forest '" + forestFileName + "' generation has an error!");
            e.printStackTrace();
            throw e;
        }

        logger.info("Your forest '" + forestFileName + "' generation completed!");

    }

    private static double generatePoint(){
        return (sr.nextDouble() * (MAX - MIN)) + MIN;
    }

}
