package com.example.demo.service;

import com.example.demo.dto.UserSignupDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 회원가입
     * @param dto
     */
    @Transactional
    public Long signup(UserSignupDto dto) {
        return userRepository.save(dto.toEntity()).getId();
    }
}
