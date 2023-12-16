package com.example.demo.service;

import com.example.demo.dto.UserSignupDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @AfterEach
    public void clear() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void User_회원가입() throws Exception {
        //given
        String email = "aaa@naver.com";
        String password = "123abc";
        String nickname = "하이";
        String name = "Lee";
        UserSignupDto dto = UserSignupDto.
                builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .build();

        //when
        //ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);
        Long id = userService.signup(dto);
        System.out.println("id = " + id);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(all.get(0).getPassword()).isEqualTo(password);

    }
}