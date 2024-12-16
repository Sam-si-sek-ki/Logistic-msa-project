package com.sparta.logistics.auth.domain.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.sparta.logistics.auth.domain.model.User;
import com.sparta.logistics.auth.domain.model.UserRole;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User save(User user);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.isDeleted = false")
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findByEmail(String email);

    @Query("""
        SELECT u FROM User u
        WHERE (:username IS NULL OR u.username LIKE %:username%)
          AND (:nickname IS NULL OR u.nickname LIKE %:nickname%)
          AND (:email IS NULL OR u.email LIKE %:email%)
          AND (:role IS NULL OR u.role = :role)
          AND (:companyId IS NULL OR u.companyId = :companyId)
          AND (:hubId IS NULL OR u.hubId = :hubId)
          AND u.isDeleted = false
    """)
    Page<User> findAllWithFilters(
            @Param("username") String username,
            @Param("nickname") String nickname,
            @Param("email") String email,
            @Param("role") UserRole role,
            @Param("companyId") String companyId,
            @Param("hubId") String hubId,
            Pageable pageable);

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true, u.deletedAt = :deletedAt, u.deletedBy = :deletedBy WHERE u.username = :username")
    void softDeleteByUsername(@Param("username") String username, @Param("deletedAt") LocalDateTime deletedAt, @Param("deletedBy") String deletedBy);
}
