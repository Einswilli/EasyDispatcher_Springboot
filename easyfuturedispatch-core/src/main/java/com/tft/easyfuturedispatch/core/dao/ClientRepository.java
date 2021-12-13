package com.tft.easyfuturedispatch.core.dao;

import com.tft.easyfuturedispatch.core.entitie.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface ClientRepository extends MongoRepository<Client,Long> {
    @Query("{ 'clientType' : ?0 }")
    List<Client> getClientsByType(String clientType);


}
