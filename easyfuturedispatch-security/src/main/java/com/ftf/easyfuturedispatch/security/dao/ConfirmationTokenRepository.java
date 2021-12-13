package com.ftf.easyfuturedispatch.security.dao;

import com.ftf.easyfuturedispatch.security.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
