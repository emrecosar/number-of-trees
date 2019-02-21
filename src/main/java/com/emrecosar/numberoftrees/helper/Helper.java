package com.emrecosar.numberoftrees.helper;

import com.emrecosar.numberoftrees.model.ApplicationProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class Helper {

    public Helper() {
    }

    public Optional<ApplicationProperties> loadProperties() throws IOException {

        InputStream inputStream = null;
        ApplicationProperties applicationProperties = null;
        try {
            Properties prop = new Properties();
            String propFileName = "application.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String forestFileName = prop.getProperty("forest-file-name");
            int fov = Integer.valueOf(prop.getProperty("field-of-view"));
            int forestSize = Integer.valueOf(prop.getProperty("forest-size"));
            String generateForest = prop.getProperty("generate-forest");
            String numberOfTrees = prop.getProperty("number-of-trees");

            applicationProperties = new ApplicationProperties(forestFileName, fov, forestSize, generateForest, numberOfTrees);

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return Optional.of(applicationProperties);

    }
}
