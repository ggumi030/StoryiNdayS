package com.sparta.storyindays.dto.post;


import com.sparta.storyindays.dto.comment.CommentResDto;
import com.sparta.storyindays.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCommentResDto {

    private PostUpdateResDto postUpdateResDto;
    private List<CommentResDto> commentResDtoList;
    private Long postLikeCount;

    public PostCommentResDto(List<CommentResDto> commentResDtoList, Post post, Long postLikeCount) {
        this.commentResDtoList = commentResDtoList;
        this.postUpdateResDto = new PostUpdateResDto(post);
        this.postLikeCount = postLikeCount;
    }
}
