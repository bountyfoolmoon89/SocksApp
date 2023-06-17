package com.example.socksapp.services;

import java.io.File;
import java.nio.file.Path;

public interface SocksFilesService {
    boolean saveToFile(String json);

    File getDataFile();

    public Path CreateTempRecipesFile(String suffix);

    public String readFromFile();

    public void cleanDataFile();
}
