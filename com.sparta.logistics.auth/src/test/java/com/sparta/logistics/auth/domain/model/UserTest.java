package com.sparta.logistics.auth.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserTest {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    @DisplayName("User 생성")
    void testCreateUser() {
        // Given
        String username = "testuser";
        String nickname = "TestNickname";
        String email = "testuser@example.com";
        String rawPassword = "Password@123";
        UserRole role = UserRole.COMPANY_USER;
        UUID companyId = UUID.randomUUID();
        UUID hubId = UUID.randomUUID();
        String slackId = "MK454H";

        // When
        User user = User.create(username, nickname, email, rawPassword, role, companyId, hubId, slackId);

        // Then
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getNickname()).isEqualTo(nickname);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getRole()).isEqualTo(role);
        assertThat(user.getCompanyId()).isEqualTo(companyId);
        assertThat(user.getHubId()).isEqualTo(hubId);
        assertThat(user.getPassword()).isNotEqualTo(rawPassword); // 원문 비밀번호와 다름
        assertThat(passwordEncoder.matches(rawPassword, user.getPassword())).isTrue(); // BCrypt 해싱된 형식인지 확인
    }
}