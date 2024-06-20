package com.sparta.storyindays.service;

import com.sparta.storyindays.dto.user.ProfileResDto;
import com.sparta.storyindays.entity.User;
import com.sparta.storyindays.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ProfileResDto getProfile(Long userId) {
        User user = findById(userId);
        ProfileResDto profileResDto = new ProfileResDto(user);
        return profileResDto;
    }

    private User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자는 존재하지 않습니다.")
        );
    }
}
