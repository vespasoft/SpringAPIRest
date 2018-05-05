package com.springapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springapirest.model.TempToken;
import com.springapirest.model.User;

@Repository
public interface TempTokenRepository extends JpaRepository<TempToken, Integer>  {
	TempToken findByUser(User user);
}
