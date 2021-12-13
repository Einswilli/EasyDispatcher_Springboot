package com.tft.easyfuturedispatch.core.dao;

import com.tft.easyfuturedispatch.core.entitie.Convoi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvoiRepository extends MongoRepository<Convoi, Long> {
}
