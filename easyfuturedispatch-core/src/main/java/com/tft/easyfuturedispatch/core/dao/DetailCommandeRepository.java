package com.tft.easyfuturedispatch.core.dao;

import com.tft.easyfuturedispatch.core.entitie.DetailCommande;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DetailCommandeRepository extends MongoRepository<DetailCommande, Long> {
}
