package com.example.socksapp.controllers;

import com.example.socksapp.services.SocksFilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.RuntimeErrorException;
import java.io.*;

@RestController
@RequestMapping("/socksFiles")
public class SocksFilesController {

    private final SocksFilesService socksFilesService;

    public SocksFilesController(SocksFilesService socksFilesService) {
        this.socksFilesService = socksFilesService;
    }

    @GetMapping("/exportSocks")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws RuntimeErrorException, FileNotFoundException {
        File file = socksFilesService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredients.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/importSocks", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file){
        socksFilesService.cleanDataFile();
        File dataFile = socksFilesService.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
