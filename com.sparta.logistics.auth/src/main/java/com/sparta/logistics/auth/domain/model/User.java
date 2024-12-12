package com.sparta.logistics.auth.domain.model;

import com.sparta.logistics.auth.libs.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "p_user")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @NotNull
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "username은 소문자와 숫자로 구성된 4자 이상, 10자 이하여야 합니다.")
    @Column(nullable = false, unique = true, length = 10, updatable = false)
    private String username;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nickname;

    @NotNull
    @Email
    @Size(max = 255)
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotNull
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "password는 대문자, 소문자, 숫자, 특수문자를 포함한 8자 이상, 15자 이하여야 합니다."
    )
    @Column(nullable = false, length = 255)
    private String password;

    @Size(max = 255)
    @Column(length = 255)
    private String slackId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column
    private UUID companyId;

    @Column
    private UUID hubId;

    /**
     * User 객체를 생성하는 팩토리 메서드.
     */
    public static User create(String username, String nickname, String email, String rawPassword, UserRole role, UUID companyId, UUID hubId) {
        User user = User.builder()
                .username(username)
                .nickname(nickname)
                .email(email)
                .password("") // 초기값 설정
                .role(role)
                .companyId(companyId != null ? companyId : null)
                .hubId(hubId != null ? hubId : null)
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

    private String encodePassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawPassword);
    }
}
