package com.example.socksapp.services.impl;

import com.example.socksapp.services.SocksFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SocksFilesServiceImpl implements SocksFilesService {

    @Value("${path.to.data.file.socks}")
    private String dataFilePath;

    @Value("${name.of.data.file.socks}")
    private String dataFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }

    @Override
    public Path CreateTempRecipesFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
