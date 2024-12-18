package com.sparta.logistics.auth.application.service;

import com.sparta.logistics.auth.application.dto.UserRequest;
import com.sparta.logistics.auth.application.dto.UserResponse;
import com.sparta.logistics.auth.domain.model.User;
import com.sparta.logistics.auth.domain.model.UserRole;
import com.sparta.logistics.auth.domain.repository.UserRepository;
import com.sparta.logistics.auth.libs.exception.ErrorCode;
import com.sparta.logistics.auth.libs.exception.GlobalException;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                request.getHubId(),
                request.getSlackId()
        );

        userRepository.save(user);

        return UserResponse.of(user);
    }

    // 사용자 단건 조회
    public UserResponse getUserByUsername(String username, String requesterRole, String requesterUsername) {
        UserRole userRole = UserRole.fromAuthority(requesterRole);

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new GlobalException(ErrorCode.USER_NOT_FOUND)
        );

        // 역할 확인 및 요청 제한
        if (userRole.isRestrictedRole() && !username.equals(requesterUsername)) {
            throw new GlobalException(ErrorCode.FORBIDDEN);
        }

        return UserResponse.of(user);
    }

    // 사용자 목록 조회

    public Page<UserResponse> getUsers(String username, String nickname, String email, UserRole role, String companyId, String hubId,
            int page, int size, String sortField, String sortDirection,
            String requesterRole, String requesterUsername) {
        UserRole userRole = UserRole.fromAuthority(requesterRole);

        // 역할 확인 및 요청 제한
        if (userRole.isRestrictedRole() && (username == null || !username.equals(requesterUsername))) {
            throw new GlobalException(ErrorCode.FORBIDDEN);
        }

        // size 제한: 10, 30, 50만 허용, 그 외는 기본값 10
        if (size != 10 && size != 30 && size != 50) {
            size = 10;
        }

        // 정렬 기본값 설정
        if (sortField == null || sortField.isEmpty()) {
            sortField = "updatedAt"; // 기본 정렬 필드
        }
        if (sortDirection == null || sortDirection.isEmpty()) {
            sortDirection = "desc"; // 기본 정렬 방향
        }

        // 정렬 생성
        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.findAllWithFilters(username, nickname, email, role, companyId, hubId, pageable)
                .map(UserResponse::of);
    }

    // 사용자 수정
    @Transactional
    public UserResponse updateUser(String username, UserRequest request) {
        // 사용자 조회
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new GlobalException(ErrorCode.USER_NOT_FOUND)
        );

        user.update(
                request.getNickname(),
                request.getEmail(),
                request.getPassword() != null ? passwordEncoder.encode(request.getPassword()) : null, // 비밀번호 암호화 후 수정
                request.getSlackId(),
                request.getRole(),
                request.getCompanyId(),
                request.getHubId()
        );

        userRepository.save(user);
        return UserResponse.of(user);
    }

    // 사용자 삭제
    @Transactional
    public void deleteUser(String username, String deletedBy) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new GlobalException(ErrorCode.USER_NOT_FOUND)
        );

        if (user.isDeleted()) {
            throw new GlobalException(ErrorCode.USER_ALREADY_DELETED);
        }

        user.setDelete(LocalDateTime.now(), deletedBy);
        userRepository.save(user);
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
        // TODO : Slack ID 유효성 검사
        return true;
    }

}
