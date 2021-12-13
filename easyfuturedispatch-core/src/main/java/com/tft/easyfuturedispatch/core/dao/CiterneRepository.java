package com.tft.easyfuturedispatch.core.dao;

import com.tft.easyfuturedispatch.core.entitie.Citerne;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiterneRepository extends MongoRepository<Citerne, Long> {
}
