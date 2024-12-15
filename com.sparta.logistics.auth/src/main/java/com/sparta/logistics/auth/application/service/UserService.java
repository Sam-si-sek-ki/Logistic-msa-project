package com.sparta.logistics.auth.application.service;

import com.sparta.logistics.auth.application.dto.UserRequest;
import com.sparta.logistics.auth.application.dto.UserResponse;
import com.sparta.logistics.auth.domain.model.User;
import com.sparta.logistics.auth.domain.repository.UserRepository;
import com.sparta.logistics.auth.libs.exception.ErrorCode;
import com.sparta.logistics.auth.libs.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 등록
    @Transactional
    public UserResponse createUser(UserRequest request) {

        // validation
        if (isUsernameExists(request.getUsername())) { // username 중복 확인
            throw new GlobalException(ErrorCode.USERNAME_ALEADY_EXISTS);
        } else if (isEmailExists(request.getEmail())) { // email 중복 확인
            throw new GlobalException(ErrorCode.EMAIL_ALEADY_EXISTS);
        } else if (!isSlackIdValid(request.getSlackId())) { // slackId 유효성 검사
            throw new GlobalException(ErrorCode.SLACKID_VALIDATION_FAILED);
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.create(
                request.getUsername(),
                request.getNickname(),
                request.getEmail(),
                encryptedPassword,
                request.getRole(),
                request.getCompanyId(),
                request.getHubId()
        );

        userRepository.save(user);

        return UserResponse.of(user);
    }

    // Username 존재 여부 확인
    public Boolean isUsernameExists(String username) {
        // username 으로 User 를 조회 후 isPresent() 로 존재유무를 리턴함
        return userRepository.findByUsername(username).isPresent();
    }

    // Email 존재 여부 확인
    public Boolean isEmailExists(String email) {
        // email 로 User 를 조회 후 isPresent() 로 존재유무를 리턴함
        return userRepository.findByEmail(email).isPresent();
    }

    // SlackId 유효성 검사
    public Boolean isSlackIdValid(String slackId) {
        // TODO : Slack ID 유효성 검사 구현
        return true;
    }
}
