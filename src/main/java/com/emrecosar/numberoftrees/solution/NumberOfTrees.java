package com.emrecosar.numberoftrees.solution;

import com.emrecosar.numberoftrees.model.Tree;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberOfTrees {

    private final Logger logger = Logger.getLogger(NumberOfTrees.class.toString());

    private Tree[] trees;

    public NumberOfTrees(String fileName) throws IOException {

        try {
            File forestFile = new File(getClass().getClassLoader().getResource(fileName).getFile());
            trees = new ObjectMapper().readValue(Files.readAllBytes(forestFile.toPath()), Tree[].class);
            logger.info("forest file now in memory! Number of trees : " + trees.length);
        } catch (FileNotFoundException fnfe) {
            logger.severe("forest file not found!" + fnfe.getMessage());
            throw fnfe;
        } catch (IOException ioe) {
            logger.severe("forest file not proper!" + ioe.getMessage());
            throw ioe;
        }

    }

    public double execute(int fieldOfView) {

        List<Tree> sortedTrees =
                Arrays.stream(trees)
                        .map(t -> {
                            t.calculateAngle();
                            return t;
                        })
                        .sorted(Comparator.comparing(Tree::getAngle))
                        .collect(Collectors.toList());

        Optional<Tree> firstTree = sortedTrees.stream()
                .map(t -> {
                            Float currentAngle = t.getAngle();
                            int currentIndex = sortedTrees.indexOf(t) + 1;
                            long count = IntStream.range(currentIndex, currentIndex + sortedTrees.size())
                                    .filter(i -> Math.abs(sortedTrees.get(i % sortedTrees.size()).getAngle() - currentAngle) <= fieldOfView)
                                    .count();

                            t.setCountofTreesTill(count);
                            return t;
                        }
                )
                .max(Comparator.comparing(Tree::getCountofTreesTill));

        return firstTree.isPresent() ? firstTree.get().getAngle() + fieldOfView / 2 : 0;

    }
}
