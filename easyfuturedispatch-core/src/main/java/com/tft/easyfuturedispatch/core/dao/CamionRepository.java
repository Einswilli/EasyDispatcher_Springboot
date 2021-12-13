package com.tft.easyfuturedispatch.core.dao;

import com.tft.easyfuturedispatch.core.entitie.Camion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends MongoRepository<Camion, Long> {
}
