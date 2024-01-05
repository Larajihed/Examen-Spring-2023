package com.example.larayedhjihed.repositories;

import com.example.larayedhjihed.entities.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    public Worker findByNic(String nic);
    public Worker findByName(String name);
}