package com.sparta.storyindays.dto.user.admin;

import com.sparta.storyindays.entity.User;
import com.sparta.storyindays.enums.user.Auth;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminAuthResDto {
    private Auth auth;

    public AdminAuthResDto(User user) {
        this.auth = user.getAuth();
    }
}
