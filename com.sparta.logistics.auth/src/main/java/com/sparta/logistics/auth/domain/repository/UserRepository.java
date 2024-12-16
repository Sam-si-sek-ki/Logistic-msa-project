package com.sparta.logistics.auth.domain.repository;

import com.sparta.logistics.auth.domain.model.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
