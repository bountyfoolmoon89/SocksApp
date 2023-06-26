package com.example.socksapp.services.impl;

import com.example.socksapp.model.Socks;
import com.example.socksapp.services.SocksService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {

    final private SocksFilesServiceImpl socksFilesService;

    private static Map<Long, Socks> noski = new HashMap<>();

    public SocksServiceImpl(SocksFilesServiceImpl socksFilesService) {
        this.socksFilesService = socksFilesService;
    }

    @Override
    public Socks getSocks(Long id) {
        if (!noski.containsKey(id)) {
            throw new IllegalArgumentException("Товар под номером " + id + " не найден");
        }
        return noski.get(id);
    }

    @Override
    public Socks addSocks(Socks socks) {
        noski.put(id++, socks);
        saveToFile();
        return socks;
    }

    @Override
    public Socks changeSocks(Long id, Socks socks) {
        noski.put(id, socks);
        saveToFile();
        return null;
    }

    @Override
    public void deleteSocks(Long id) {
        if (!noski.containsKey(id)) {
            throw new IllegalArgumentException("Товар под номером " + id + " не найден");
        }
        noski.remove(id);
    }

    @Override
    public String printAllSocks(Map<Long, Socks> socks) {
        return socks.toString();
    }

    public static long id;

    @Override
    public void init() {
        readFromFile();
    }

    @Override
    public void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(noski);
            socksFilesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readFromFile() {
        try {
            String json = socksFilesService.readFromFile();
            noski = new ObjectMapper().readValue(json, new TypeReference<Map<Long, Socks>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
