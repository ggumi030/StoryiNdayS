package com.sparta.storyindays.dto.user;

import com.sparta.storyindays.entity.User;
import com.sparta.storyindays.enums.user.Auth;
import com.sparta.storyindays.enums.user.State;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileResDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String instroduction;
    private Auth auth;
    private State state;
    private int postLike;
    private int commentLike;

    public ProfileResDto(User user, int postLike, int commentLike) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.instroduction = user.getIntroduction();
        this.auth = user.getAuth();
        this.state = user.getState();
        this.postLike = postLike;
        this.commentLike = commentLike;
    }
}
