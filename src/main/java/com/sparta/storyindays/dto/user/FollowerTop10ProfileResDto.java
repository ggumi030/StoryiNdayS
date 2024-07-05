package com.sparta.storyindays.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowerTop10ProfileResDto {

    private String username;
    private String name;
    private String introduction;
    private Long follower;

    public FollowerTop10ProfileResDto(String username, String name, String introduction) {
        this.username = username;
        this.name = name;
        this.introduction = introduction;
    }

    public FollowerTop10ProfileResDto(String username, String name, String introduction, Long follower) {
        this.username = username;
        this.name = name;
        this.introduction = introduction;
        this.follower = follower;
    }
}

