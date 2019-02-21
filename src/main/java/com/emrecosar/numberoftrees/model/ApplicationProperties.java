package com.emrecosar.numberoftrees.model;

public class ApplicationProperties {

    private String forestFileName;
    private int fieldOfView;
    private int forestSize;
    private String generateForest;
    private String numberOfTrees;

    public ApplicationProperties(String forestFileName, int fieldOfView, int forestSize, String generateForest, String numberOfTrees) {
        this.forestFileName = forestFileName;
        this.fieldOfView = fieldOfView;
        this.forestSize = forestSize;
        this.generateForest = generateForest;
        this.numberOfTrees = numberOfTrees;
    }

    public int getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(int fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public String getForestFileName() {
        return forestFileName;
    }

    public void setForestFileName(String forestFileName) {
        this.forestFileName = forestFileName;
    }

    public int getForestSize() {
        return forestSize;
    }

    public void setForestSize(int forestSize) {
        this.forestSize = forestSize;
    }

    public String getGenerateForest() {
        return generateForest;
    }

    public void setGenerateForest(String generateForest) {
        this.generateForest = generateForest;
    }

    public String getNumberOfTrees() {
        return numberOfTrees;
    }

    public void setNumberOfTrees(String numberOfTrees) {
        this.numberOfTrees = numberOfTrees;
    }
}
