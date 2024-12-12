package com.sparta.logistics.auth.application.service;

import com.sparta.logistics.auth.application.dto.UserRequest;
import com.sparta.logistics.auth.application.dto.UserResponse;
import com.sparta.logistics.auth.domain.model.User;
import com.sparta.logistics.auth.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserRequest request) {

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

}
