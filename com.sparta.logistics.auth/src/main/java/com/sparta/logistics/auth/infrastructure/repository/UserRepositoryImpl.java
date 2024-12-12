package com.sparta.logistics.auth.infrastructure.repository;

import com.sparta.logistics.auth.domain.model.User;
import com.sparta.logistics.auth.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<User, String>, UserRepository {

    User save(User user);
}
