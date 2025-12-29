package org.basic.microservice.repository;

import org.basic.microservice.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE name = :userName", nativeQuery = true)
    List<User> getUserByName(@Param("userName") String userName);
}
