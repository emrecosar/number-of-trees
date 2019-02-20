package com.emrecosar.numberoftrees.solution;

import com.emrecosar.numberoftrees.model.Tree;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberOfTrees {

    private final Logger logger = LoggerFactory.getLogger(NumberOfTrees.class);

    private Tree[] trees;

    public NumberOfTrees(String fileName) throws IOException {

        try {
            File forestFile = ResourceUtils.getFile("classpath:" + fileName);
            trees = new ObjectMapper().readValue(Files.readAllBytes(forestFile.toPath()), Tree[].class);
            logger.info("forest file now in memory! Number of trees : " + trees.length);
        } catch (FileNotFoundException fnfe) {
            logger.error("forest file not found!", fnfe);
            throw fnfe;
        } catch (IOException ioe) {
            logger.error("forest file not proper!", ioe);
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
