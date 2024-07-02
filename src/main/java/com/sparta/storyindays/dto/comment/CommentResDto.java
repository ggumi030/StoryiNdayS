package com.sparta.storyindays.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResDto {

    private Long commentId;

    private String username;

    private String comment;

    private LocalDateTime createdAt;

    private Long commentLikeCount = 0L;

    public CommentResDto(Long commentId, String username, String comment, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.username = username;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public CommentResDto(Long commentId, String username, String comment, LocalDateTime createdAt, Long commentLikeCount) {
        this.commentId = commentId;
        this.username = username;
        this.comment = comment;
        this.createdAt = createdAt;
        this.commentLikeCount = commentLikeCount;
    }
}
