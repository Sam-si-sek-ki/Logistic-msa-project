package com.sparta.logistics.auth.domain.model;

import com.sparta.logistics.auth.libs.exception.ErrorCode;
import com.sparta.logistics.auth.libs.exception.GlobalException;
import com.sparta.logistics.auth.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "p_user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @Column(nullable = false, unique = true, length = 10, updatable = false)
    private String username;

    @Column(nullable = false, length = 100)
    private String nickname;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 255)
    private String slackId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column
    private UUID companyId;

    @Column
    private UUID hubId;

    /**
     * User 객체를 생성합니다.
     * @param username 사용자 이름
     * @param nickname 사용자 닉네임
     * @param email 이메일
     * @param rawPassword 원시 비밀번호
     * @param role 사용자 역할
     * @param companyId 회사 ID
     * @param hubId 허브 ID
     * @return User 객체
     */
    public static User create(String username, String nickname, String email, String rawPassword, UserRole role, UUID companyId, UUID hubId) {
        User user = User.builder()
                .username(username)
                .nickname(nickname)
                .email(email)
                .role(role)
                .companyId(companyId)
                .hubId(hubId)
                .build();

        user.setPassword(rawPassword); // 비밀번호 해싱 및 설정
        return user;
    }

    /**
     * 비밀번호를 해싱하여 설정합니다.
     */
    public void setPassword(String rawPassword) {
        this.password = encodePassword(rawPassword);
    }

    /**
     * 비밀번호를 해싱합니다.
     * @param rawPassword 원시 비밀번호
     * @return 해싱된 비밀번호
     */
    private String encodePassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawPassword);
    }
}
