package com.sparta.logistics.auth.domain.repository;

import com.sparta.logistics.auth.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User save(User user);
}
