package com.example.socksapp.services;

import com.example.socksapp.model.Socks;

import java.util.Map;

public interface SocksService {
    Socks getSocks(Long id);

    Socks addSocks(Socks socks);

    Socks changeSocks(Long id, Socks socks);

    void deleteSocks(Long id);

    String printAllSocks(Map<Long, Socks> socks);

    void init();

    void saveToFile();

    void readFromFile();
}
