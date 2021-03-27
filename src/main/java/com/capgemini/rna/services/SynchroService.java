package com.capgemini.rna.services;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Service
@Scope("singleton")
@Log
public class SynchroService {

    private final ConcurrentHashMap<String, Semaphore> semaphores = new ConcurrentHashMap<>();

    public void acquireExecutionPermission(String id) throws InterruptedException {
        semaphores.putIfAbsent(id, new Semaphore(1));
        semaphores.get(id).acquire();
    }

    public void release(String id) {
        semaphores.get(id).release();
    }

}
