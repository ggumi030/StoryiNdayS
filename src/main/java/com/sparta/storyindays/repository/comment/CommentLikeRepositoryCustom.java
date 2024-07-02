package com.sparta.storyindays.repository.comment;

import com.sparta.storyindays.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepositoryCustom {
    Long getCommentLikeCount(Comment comment);

}
